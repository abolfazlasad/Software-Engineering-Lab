from pydantic import BaseModel

class UserIdForm(BaseModel):
    user_id: str

class UsernamePasswordForm(BaseModel):
    username: str
    password: str

class UserForm(UsernamePasswordForm):
    email: str = None
    full_name: str = None

class LoginResponse(BaseModel):
    access_token: str = None
    token_type: str = None
    detail: str = None

class UserResponse(BaseModel):
    id: int = None
    username: str = None
    hashed_password: str = None
    email: str = None
    full_name: str = None
    detail: object = None
