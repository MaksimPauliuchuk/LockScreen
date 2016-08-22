package com.lockscreen.errorHandling;

import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException>
{
    private static final Logger LOGGER = Logger.getLogger(ServiceExceptionMapper.class.getName());

    @Override
    public final Response toResponse(final ServiceException anEx)
    {
        LOGGER.info(this.getClass().getName() + " exception intercepted.\n" + anEx.toString());
        return Response.status(anEx.getStatus()).entity(anEx.getEntity().toJson()).type(MediaType.APPLICATION_JSON)
                .build();
    }
}
