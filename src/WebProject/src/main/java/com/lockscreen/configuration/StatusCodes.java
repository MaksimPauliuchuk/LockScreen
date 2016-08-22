package com.lockscreen.configuration;

import java.util.HashMap;
import java.util.Map;

public enum StatusCodes
{

    // App errors.
    INTERNAL_ERROR(1001, "INTERNAL_ERROR"),

    // USER
    USER_EXIST(2001, "User is exist"),
    USERNAME_AND_PASSWORD_NOT_MATCHED(2002, "Username and password not matched"),
    TOKEN_IS_REQUIRED(2002, "Token is required"),
    TOKEN_IS_EXPIRED(2003, "Token is expired");

    private final int code;
    private final String description;

    private StatusCodes(int code, String description)
    {
        this.code = code;
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public int getCode()
    {
        return code;
    }

    public Map<String, Object> toJson()
    {
        Map<String, Object> map = new HashMap<>();
        map.put("code", Integer.toString(getCode()));
        map.put("message", getDescription());
        return map;
    }

    public static Map<String, Object> toJson(int aCode, String message)
    {

        Map<String, Object> map = new HashMap<>();
        map.put("code", Integer.toString(aCode));
        map.put("message", message);
        return map;
    }

    @Override
    public String toString()
    {
        return "[code = " + getCode() + ", description = " + getDescription() + "]";
    }

}
