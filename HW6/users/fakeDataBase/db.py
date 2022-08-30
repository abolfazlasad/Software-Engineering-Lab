import json
from datastructures.ds import UserInDb


users_file = 'fakeDataBase/users.json'


def get_all_users():
    with open(users_file) as json_file:
        users = json.load(json_file)
        for user in users:
            yield UserInDb(**user)

def dump_users(users: list, db: str = users_file):
    with open(db, 'w') as json_file:
        json.dump(users, json_file, indent=4)



def get_user_by_id(uid: int):
    users = list(filter(lambda u: u.id == uid, get_all_users()))
    return users[0] if users else None

def get_user_by_username(uname: str):
    users = list(filter(lambda u: u.username == uname, get_all_users()))
    return users[0] if users else None

def get_user_by_email(email: str):
    users = list(filter(lambda u: u.email == email, get_all_users()))
    return users[0] if users else None

def insert_user(data: dict, hashed_password: str):
    try:
        last_id = max(map(lambda u: u.id, get_all_users()))
        pk = last_id + 1
    except ValueError:
        pk = 1

    data.update({
        'id': pk,
        'hashed_password': hashed_password,
    })

    user_in_db = UserInDb(**data)
    users = list(map(lambda u: u.dict(), get_all_users()))
    users.append(user_in_db.dict())

    dump_users(users)

    return user_in_db
