package com.lockscreen.filters;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.lockscreen.configuration.StatusCodes;
import com.lockscreen.dao.TokenDAO;
import com.lockscreen.models.Token;

@Auth
@Provider
public class AuthRequestFilter implements ContainerRequestFilter
{
    private static final Logger LOGGER = Logger.getLogger(AuthRequestFilter.class.getName());

    @Inject
    private TokenDAO itsTokenDAO;

    @Override
    public final void filter(final ContainerRequestContext aRequestContext) throws IOException
    {
        String authToken = aRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (null == authToken)
        {
            aRequestContext.abortWith(
                    Response.status(Status.UNAUTHORIZED).entity(StatusCodes.TOKEN_IS_REQUIRED.toJson()).build());
            return;
        }

        Token token = itsTokenDAO.get(authToken);

        if (null == token)
        {
            aRequestContext.abortWith(
                    Response.status(Status.UNAUTHORIZED).entity(StatusCodes.TOKEN_IS_EXPIRED.toJson()).build());
            return;
        }

        if (!itsTokenDAO.updateTime(token.getId()))
        {
            LOGGER.severe("TOKEN TIME ISN'T UPDATED ON AuthRequestFilter");
        }
    }
}
