import aiohttp
import functools
from fastapi import Request, Response, HTTPException, status

from importlib import import_module

from utility.network import make_request
from authentication.auth import decode_access_token
from authentication.exceptions import (
    AuthTokenMissing,
    AuthTokenExpired,
    AuthTokenCorrupted)


def route(
        request_method,
        external_path: str,
        internal_path: str,
        status_code: int,
        payload_key: str,
        param_key: str,
        service_url: str,
        authentication_required: bool = False,
        post_processing_func: str = None,
        response_model: str = None
):
    if response_model:
        response_model = import_function(response_model)

    app_any = request_method(
        external_path, status_code=status_code,
        response_model=response_model
    )

    def wrapper(f):
        @app_any
        @functools.wraps(f)
        async def inner(request: Request, response: Response, **kwargs):
            service_headers = {}

            if authentication_required:
                # authentication
                authorization = request.headers.get('authorization')
                exc = None
                try:
                    token_payload = decode_access_token(authorization)
                except (AuthTokenMissing,
                        AuthTokenExpired,
                        AuthTokenCorrupted) as e:
                    exc = str(e)
                except Exception as e:
                    exc = str(e)
                finally:
                    if exc:
                        raise HTTPException(
                            status_code=status.HTTP_401_UNAUTHORIZED,
                            detail=exc,
                            headers={'WWW-Authenticate': 'Bearer'},
                        )

                # authorization
                is_user_eligible = token_payload['id'] == kwargs.get('user_id')
                if not is_user_eligible:
                    raise HTTPException(
                        status_code=status.HTTP_403_FORBIDDEN,
                        detail='You are not allowed to access this scope.',
                        headers={'WWW-Authenticate': 'Bearer'},
                    )



            url = f'{service_url}{internal_path}'
            param_obj = kwargs.get(param_key)
            if param_obj:
                url += '/' + str(param_obj)

            method = request.scope['method'].lower()
            payload_obj = kwargs.get(payload_key)
            payload = payload_obj.dict() if payload_obj else {}

            try:
                resp_data, status_code_from_service = await make_request(
                    url=url,
                    method=method,
                    data=payload,
                    headers=service_headers,
                )
            except aiohttp.client_exceptions.ClientConnectorError:
                raise HTTPException(
                    status_code=status.HTTP_503_SERVICE_UNAVAILABLE,
                    detail='Service is unavailable.',
                    headers={'WWW-Authenticate': 'Bearer'},
                )
            except aiohttp.client_exceptions.ContentTypeError:
                raise HTTPException(
                    status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
                    detail='Service error.',
                    headers={'WWW-Authenticate': 'Bearer'},
                )

            response.status_code = status_code_from_service

            if all([
                status_code_from_service == status_code,
                post_processing_func
            ]):
                post_processing_f = import_function(post_processing_func)
                resp_data = post_processing_f(resp_data)

            return resp_data

    return wrapper


def import_function(method_path):
    module, method = method_path.rsplit('.', 1)
    mod = import_module(module)
    return getattr(mod, method, lambda *args, **kwargs: None)
