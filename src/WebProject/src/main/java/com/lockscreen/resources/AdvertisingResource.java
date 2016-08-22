package com.lockscreen.resources;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lockscreen.errorHandling.ServiceException;
import com.lockscreen.filters.Auth;

@Auth
@Path("adv")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdvertisingResource
{
    private static final Logger LOGGER = Logger.getLogger(AdvertisingResource.class.getName());

    @GET
    @Path("{id}")
    public final Response getById(@PathParam("id") final int anId) throws ServiceException
    {
        LOGGER.info(getClass().getName() + " - signUp");

        System.out.println(anId);

        return Response.status(Status.OK).build();
    }

}
