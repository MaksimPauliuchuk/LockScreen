package com.lockscreen.dao;

import com.lockscreen.models.User;

public interface UserDAO
{
    int add(User aUser);

    User getUserById(int anId);

    User getUserByLogin(String aLogin);

    int getIdByLoginAndPasswd(User aUser);
}
