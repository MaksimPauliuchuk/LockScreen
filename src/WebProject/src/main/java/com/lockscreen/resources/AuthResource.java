package com.lockscreen.resources;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lockscreen.errorHandling.ServiceException;
import com.lockscreen.filters.Auth;
import com.lockscreen.models.User;
import com.lockscreen.services.AuthService;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource
{
    private static final Logger LOGGER = Logger.getLogger(AuthResource.class.getName());

    @Inject
    private AuthService itsAuthService;
    @HeaderParam(HttpHeaders.AUTHORIZATION)
    private String itsToken;

    @POST
    @Path("signUp")
    public final Response signUp(final User aUser)
            throws ServiceException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        LOGGER.info(getClass().getName() + " - signUp");
        String token = itsAuthService.signUp(aUser);

        return Response.status(Status.OK).header(HttpHeaders.AUTHORIZATION, token).build();
    }

    @POST
    @Path("signIn")
    public final Response signIn(final User aUser)
            throws ServiceException, NoSuchAlgorithmException, InvalidKeySpecException
    {
        LOGGER.info(getClass().getName() + " - signIn");
        String token = itsAuthService.signIn(aUser);

        return Response.status(Status.OK).header(HttpHeaders.AUTHORIZATION, token).build();
    }

    @Auth
    @POST
    @Path("signOut")
    public final Response signOut() throws ServiceException
    {
        LOGGER.info(getClass().getName() + " - signOut");
        itsAuthService.signOut(itsToken);

        return Response.status(Status.OK).build();
    }
}
