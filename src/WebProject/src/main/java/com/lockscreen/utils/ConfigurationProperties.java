package com.lockscreen.utils;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public final class ConfigurationProperties
{

    private static final Logger LOGGER = Logger.getLogger(ConfigurationProperties.class.getName());
    private static final Properties theirQueries = new Properties();
    private static final Properties theirProperties = new Properties();
    private static String theirDBUri = null;
    private static String theirDBUser = null;
    private static String theirDBPassword = null;

    private static final String DB_USER = "db.user";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_URI = "db.uri";

    private ConfigurationProperties()
    {

        // Utility class
    }

    static
    {
        try
        {
            theirProperties.load(ConfigurationProperties.class.getResourceAsStream("/db.properties"));
            theirQueries.load(ConfigurationProperties.class.getResourceAsStream("/queries.properties"));
        }
        catch (IOException e)
        {
            LOGGER.severe("Resource files not found.");
        }
    }

    public static Properties getQueries()
    {
        return theirQueries;
    }

    public static String getDBUser()
    {
        LOGGER.finest("getDBUser " + theirDBUser);
        if (null == theirDBUser)
        {
            theirDBUser = theirProperties.getProperty(DB_USER);
        }
        return theirDBUser;
    }

    public static String getDBPassword()
    {
        LOGGER.finest("getDBPassword " + theirDBPassword);
        if (null == theirDBPassword)
        {
            theirDBPassword = theirProperties.getProperty(DB_PASSWORD);
        }
        return theirDBPassword;
    }

    public static String getDBUri()
    {
        LOGGER.finest("getDBUri " + theirDBUri);
        if (null == theirDBUri)
        {
            theirDBUri = theirProperties.getProperty(DB_URI);
        }
        return theirDBUri;
    }
}
