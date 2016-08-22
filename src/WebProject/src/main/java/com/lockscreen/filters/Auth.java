package com.lockscreen.filters;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.ws.rs.NameBinding;

/**
 * Annotation for web services that needs to validate token.
 * 
 * @author emergency
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth
{

}
