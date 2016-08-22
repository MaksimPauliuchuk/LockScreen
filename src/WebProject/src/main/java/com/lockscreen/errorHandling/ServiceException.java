package com.lockscreen.errorHandling;

import javax.ws.rs.core.Response.Status;

import com.lockscreen.configuration.StatusCodes;

public class ServiceException extends Exception
{
    private static final long serialVersionUID = 1452546920616794737L;

    private Integer itsStatus;

    private StatusCodes itsEntity;

    public ServiceException(final int anStatus, final StatusCodes anEntity)
    {
        itsStatus = anStatus;
        itsEntity = anEntity;
    }

    public ServiceException(final Status anStatus, final StatusCodes anEntity)
    {
        itsStatus = anStatus.getStatusCode();
        itsEntity = anEntity;
    }

    public final int getStatus()
    {
        return itsStatus;
    }

    public final void setStatus(final int anStatus)
    {
        this.itsStatus = anStatus;
    }

    public final StatusCodes getEntity()
    {
        return itsEntity;
    }

    public final void setEntity(final StatusCodes anEntity)
    {
        this.itsEntity = anEntity;
    }

    @Override
    public String toString()
    {
        return "ServiceException [itsStatus=" + itsStatus + ", itsEntity=" + itsEntity + "]";
    }
}
