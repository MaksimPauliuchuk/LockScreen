package com.lockscreen.dao;

import com.lockscreen.models.Token;

public interface TokenDAO
{
    int add(Token aToken);

    Token get(String aToken);

    boolean updateTime(int anId);

    boolean deleteByValue(String aToken);
}
