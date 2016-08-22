package com.lockscreen.models;

public class User
{
    private int itsId;

    private String itsLogin;

    private String itsPassword;

    private String itsEmail;

    private String itsPhone;

    private int itsGroupId;

    public User()
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

    public final String getLogin()
    {
        return itsLogin;
    }

    public final void setLogin(final String aLogin)
    {
        this.itsLogin = aLogin;
    }

    public final String getPassword()
    {
        return itsPassword;
    }

    public final void setPassword(final String aPassword)
    {
        this.itsPassword = aPassword;
    }

    public final String getEmail()
    {
        return itsEmail;
    }

    public final void setEmail(final String anEmail)
    {
        this.itsEmail = anEmail;
    }

    public final String getPhone()
    {
        return itsPhone;
    }

    public final void setPhone(final String aPhone)
    {
        this.itsPhone = aPhone;
    }

    public final int getGroupId()
    {
        return itsGroupId;
    }

    public final void setGroupId(final int aGroupId)
    {
        this.itsGroupId = aGroupId;
    }
}
