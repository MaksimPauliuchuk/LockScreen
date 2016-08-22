package com.lockscreen.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

import com.lockscreen.dao.UserDAO;
import com.lockscreen.models.User;
import com.lockscreen.utils.ConfigurationProperties;
import com.lockscreen.utils.DBConnection;

public class UserDAOImpl implements UserDAO
{
    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class.getName());
    private static final Properties QUERIES = ConfigurationProperties.getQueries();

    @Override
    public final int add(final User aUser)
    {
        LOGGER.info("Call UserDAOImpl add(" + aUser + ")");

        String query = QUERIES.getProperty("user.add");

        int id = -1;

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS))
        {
            statement.setString(1, aUser.getLogin());
            statement.setString(2, aUser.getPassword());
            statement.setString(3, aUser.getEmail());
            statement.setString(4, aUser.getPhone());
            statement.setInt(5, aUser.getGroupId());

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
    public final User getUserByLogin(final String aLogin)
    {
        LOGGER.info("Call UserDAOImpl getUserByLogin(" + aLogin + ")");

        User user = null;
        String query = QUERIES.getProperty("user.get_by_login");

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, aLogin);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setGroupId(resultSet.getInt("fk_group"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public final User getUserById(final int anId)
    {
        LOGGER.info("Call UserDAOImpl getUserById(" + anId + ")");

        User user = null;
        String query = QUERIES.getProperty("user.get_by_id");

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setInt(1, anId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setGroupId(resultSet.getInt("fk_group"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public final int getIdByLoginAndPasswd(final User aUser)
    {
        LOGGER.info("Call UserDAOImpl getIdByLoginAndPasswd(" + aUser + ")");

        int id = -1;
        String query = QUERIES.getProperty("user.get_id_by_login_and_passwd");

        try (Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query))
        {
            statement.setString(1, aUser.getLogin());
            statement.setString(2, aUser.getPassword());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                id = resultSet.getInt("id");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return id;
    }
}
