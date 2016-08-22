package com.lockscreen.configuration;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import com.lockscreen.dao.TokenDAO;
import com.lockscreen.dao.UserDAO;
import com.lockscreen.dao.impl.TokenDAOImpl;
import com.lockscreen.dao.impl.UserDAOImpl;
import com.lockscreen.services.AuthService;
import com.lockscreen.services.UserService;

public class InjectBinder extends AbstractBinder
{
    @Override
    protected final void configure()
    {
        bind(AuthService.class).to(AuthService.class);
        bind(UserService.class).to(UserService.class);

        bind(UserDAOImpl.class).to(UserDAO.class);
        bind(TokenDAOImpl.class).to(TokenDAO.class);
    }
}
