package com.facebook.DAO.Impl;

import com.facebook.DAO.UserDAO;
import com.facebook.DAOConnection.DataSourceConnection;
import com.facebook.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
     * @return Returns the singleton instance of the user service implementation class.
     */
    public static UserDAOImpl getInstance() {
        if (null == userDAOImpl) {
            userDAOImpl = new UserDAOImpl();
        }

        return userDAOImpl;
    }

    /**
     * <p>
     * Retrieves a user from the database through id
     * </p>
     *
     * @param id Retrieves the id of the user
     * @return Returns {@link User}with the specified ID if found, otherwise null
     */
    public User get(final Long id) {
        final String sql = "select * from sign where id = ?";

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
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }

    /**
     * <p>
     * Deletes a user from the database based on id
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
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    /**
     * <p>
     * Updates the details of a user in the database
     * </p>
     *
     * @param user Refers {@link User}user object containing the updated user information.
     * @return true if the user information is successfully updated, false otherwise.
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
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }
}
