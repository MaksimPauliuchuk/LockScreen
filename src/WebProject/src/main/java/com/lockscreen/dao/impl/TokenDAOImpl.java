package com.lockscreen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Logger;

import com.lockscreen.dao.TokenDAO;
import com.lockscreen.models.Token;
import com.lockscreen.utils.ConfigurationProperties;
import com.lockscreen.utils.DBConnection;

public class TokenDAOImpl implements TokenDAO
{
    private static final Logger LOGGER = Logger.getLogger(TokenDAOImpl.class.getName());
    private static final Properties QUERIES = ConfigurationProperties.getQueries();

    @Override
    public int add(final Token aToken)
    {
        LOGGER.info("Call TokenDAOImpl add(" + aToken + ")");

        String query = QUERIES.getProperty("token.add");

        int id = -1;

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, aToken.getToken());
            statement.setInt(2, aToken.getUserId());

            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                id = rs.getInt(1);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return id;
    }

    @Override
    public Token get(final String aToken)
    {
        LOGGER.info("Call TokenDAOImpl get(" + aToken + ")");

        Token token = null;
        String query = QUERIES.getProperty("token.get");

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, aToken);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(resultSet.getDate("time"));
                token = new Token();
                token.setId(resultSet.getInt("id"));
                token.setTime(cal);
                token.setToken(aToken);
                token.setUserId(resultSet.getInt("fk_user"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public boolean updateTime(final int anId)
    {
        LOGGER.info("Call TokenDAOImpl updateTime(" + anId + ")");

        String query = QUERIES.getProperty("token.update_time");

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, anId);
            statement.executeUpdate();
            status = true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public boolean deleteByValue(final String aToken)
    {
        LOGGER.info("Call TokenDAOImpl deleteByValue(" + aToken + ")");

        String query = QUERIES.getProperty("token.delete_by_value");

        boolean status = false;

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, aToken);
            statement.executeUpdate();
            status = true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return status;
    }

}
