package com.lockscreen.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public final class DBConnection
{
	private static final String URL;
	private static Connection theirConnection;
	private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName());
	private static final Properties PROPERTIES = new Properties();

	static
	{
		URL = ConfigurationProperties.getDBUri();
		PROPERTIES.setProperty("user", ConfigurationProperties.getDBUser());
		PROPERTIES.setProperty("password", ConfigurationProperties.getDBPassword());
		PROPERTIES.setProperty("useUnicode", "true");
		PROPERTIES.setProperty("characterEncoding", "utf-8");
	}

	private DBConnection()
	{
		// utility class
	}

	public static Connection getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			theirConnection = DriverManager.getConnection(URL, PROPERTIES);
		}
		catch (SQLException e)
		{
			LOGGER.severe("error while connecting to DB on getConnection DBConnection class");
		}
		catch (ClassNotFoundException e)
		{
			LOGGER.severe("class com.mysql.jdbc.Driver not found on getConnection DBConnection class");
		}
		return theirConnection;
	}
}
