from pydantic import BaseModel

class UsernamePasswordForm(BaseModel):
    username: str
    password: str

class UserForm(UsernamePasswordForm):
    email: str = None
    full_name: str = None

class UserInDb(BaseModel):
    id: int
    username: str
    hashed_password: str
    email: str = None
    full_name: str = None
