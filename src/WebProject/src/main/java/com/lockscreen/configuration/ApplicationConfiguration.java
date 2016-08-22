package com.lockscreen.configuration;

import org.glassfish.jersey.server.ResourceConfig;

import com.lockscreen.errorHandling.ServiceExceptionMapper;
import com.lockscreen.filters.AuthRequestFilter;

/**
 * Here is stored configuration of application.
 * 
 * @author Maksim
 */
public class ApplicationConfiguration extends ResourceConfig
{
    /**
     * Initialize configuration.
     */
    public ApplicationConfiguration()
    {
        register(new InjectBinder());
        register(new ServiceExceptionMapper());
        register(new AuthRequestFilter());
        packages(true, Constants.RESOURCES_CONTAINER);
    }
}
