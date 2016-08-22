package com.lockscreen.models;

import java.util.Calendar;

public class Token
{
    private int itsId;

    private String itsToken;

    private Calendar itsTime;

    private int itsUserId;

    public Token()
    {

    }

    public final int getId()
    {
        return itsId;
    }

    public final void setId(final int anId)
    {
        this.itsId = anId;
    }

    public final String getToken()
    {
        return itsToken;
    }

    public final void setToken(final String aToken)
    {
        this.itsToken = aToken;
    }

    public final Calendar getTime()
    {
        return itsTime;
    }

    public final void setTime(final Calendar aTime)
    {
        this.itsTime = aTime;
    }

    public final int getUserId()
    {
        return itsUserId;
    }

    public final void setUserId(final int aUserId)
    {
        this.itsUserId = aUserId;
    }
}
