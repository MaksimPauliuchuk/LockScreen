package com.lockscreen.services;

import java.util.logging.Logger;

import javax.inject.Inject;

import com.lockscreen.dao.UserDAO;
import com.lockscreen.models.User;

public class UserService
{
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    @Inject
    private UserDAO itsUserDAO;

    public final int addUser(final User aUser)
    {
        int id = itsUserDAO.add(aUser);
        return id;
    }

    public final User getUserByLogin(final String aLogin)
    {
        User user = itsUserDAO.getUserByLogin(aLogin);
        return user;
    }

    public final User getUserById(final int anId)
    {
        User user = itsUserDAO.getUserById(anId);
        return user;
    }
}
