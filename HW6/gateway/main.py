from fastapi import FastAPI, status, Request, Response

from conf import settings
from utility.core import route

from datastructures.users import (UsernamePasswordForm,
                                  UserForm)

app = FastAPI()


@route(
    request_method=app.post,
    external_path='/api/login',
    internal_path='/login',
    status_code=status.HTTP_200_OK,
    payload_key='username_password',
    param_key=None,
    service_url=settings.USERS_SERVICE_URL,
    authentication_required=False,
    post_processing_func='utility.post_processing.access_token_generate_handler',
    response_model='datastructures.users.LoginResponse'
)
async def login(username_password: UsernamePasswordForm,
                request: Request, response: Response):
    pass


@route(
    request_method=app.post,
    external_path='/api/signup',
    internal_path='/signup',
    status_code=status.HTTP_201_CREATED,
    payload_key='user',
    param_key=None,
    service_url=settings.USERS_SERVICE_URL,
    authentication_required=False,
    post_processing_func=None,
    response_model='datastructures.users.UserResponse',
)
async def signup(user: UserForm, request: Request, response: Response):
    pass


@route(
    request_method=app.get,
    external_path='/api/users/{user_id}',
    internal_path='/users',
    status_code=status.HTTP_200_OK,
    payload_key=None,
    param_key='user_id',
    service_url=settings.USERS_SERVICE_URL,
    authentication_required=True,
    post_processing_func=None,
    response_model='datastructures.users.UserResponse',
)
async def get_user(user_id: int, request: Request, response: Response):
    pass