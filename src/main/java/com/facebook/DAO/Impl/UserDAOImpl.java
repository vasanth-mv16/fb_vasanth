package com.facebook.DAO.Impl;

import com.facebook.DAO.UserDAO;
import com.facebook.DAOConnection.DataSourceConnection;
import com.facebook.customException.DataBaseAccessException;
import com.facebook.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Provides the following services for the user
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class UserDAOImpl implements UserDAO {

    private static UserDAOImpl userDAOImpl;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    public UserDAOImpl() {
    }

    /**
     * <p>
     * Gets the instance of user service implementation
     * </p>
     *
     * @return Returns instance of the user service implementation class
     */
    public static UserDAOImpl getInstance() {
        if (null == userDAOImpl) {
            userDAOImpl = new UserDAOImpl();
        }

        return userDAOImpl;
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param id Retrieves the id of the user
     * @return Returns {@link User}with the specified id if found, otherwise null
     */
    public User get(final Long id) {
        final String sql = "select id, name, mobilenumber,email, password, dateofbirth from sign where id = ?";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final User user = new User();

                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setMobileNumber(resultSet.getString("mobilenumber"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setDateOfBirth(resultSet.getString("dateofbirth"));
                connection.commit();

                return user;
            }
        } catch (SQLException exception) {
            throw new DataBaseAccessException("Problem in Database connection, check it out");
        }

        return null;
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param id Refers the id of the user to delete
     * @return Returns true if the user was successfully deleted, otherwise false
     */
    public boolean delete(final Long id) {
        final String sql = "delete from sign where id = ?";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();

            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param user Reference {@link User} that user to be updated
     * @param id   Refers the id for update the user
     * @return Returns true if the user information has been updated, false otherwise
     */
    public boolean update(final User user, final Long id) {
        final String sql = "update sign set name = ?, mobilenumber = ?, email = ?, password = ?, dateofbirth = ? where id = ?";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getMobileNumber());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getDateOfBirth());
            preparedStatement.setLong(6, id);
            preparedStatement.execute();
            connection.commit();

            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }
}
