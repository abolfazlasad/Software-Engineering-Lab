from fastapi import FastAPI, HTTPException, status

from datastructures.ds import UsernamePasswordForm, UserForm
from authentication.auth import verify_password, get_password_hash
from fakeDataBase.db import (get_user_by_username,
                     get_user_by_email,
                     get_user_by_id,
                     insert_user)


app = FastAPI()


@app.post('/login', status_code=status.HTTP_200_OK)
async def login(form_data: UsernamePasswordForm):
    user_in_db = get_user_by_username(form_data.username)
    if not user_in_db:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail='User not found with this username.',
        )
    verified = verify_password(form_data.password, user_in_db.hashed_password)
    if not verified:
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail='Password is wrong.',
        )
    return user_in_db


@app.post('/signup', status_code=status.HTTP_201_CREATED)
async def signup(user: UserForm):
    user_in_db = get_user_by_username(user.username)
    if user_in_db:
        raise HTTPException(
            status_code=status.HTTP_409_CONFLICT,
            detail='There is already another user with this username.',
        )
    user_in_db = get_user_by_email(user.email)
    if user_in_db:
        raise HTTPException(
            status_code=status.HTTP_409_CONFLICT,
            detail='There is already another user with this email.',
        )
    hashed_password = get_password_hash(user.password)
    data = user.dict()
    user_in_db = insert_user(data, hashed_password)
    return user_in_db


@app.get('/users/{user_id}', status_code=status.HTTP_200_OK)
async def get_user(user_id: int):
    user_in_db = get_user_by_id(user_id)
    if not user_in_db:
        raise HTTPException(
            status_code=status.HTTP_404_NOT_FOUND,
            detail='User not found with this id.',
        )
    return user_in_db

