package com.facebook.DAO.Impl;

import com.facebook.DAO.UserAuthenticationDAO;
import com.facebook.DAOConnection.DataSourceConnection;
import com.facebook.customException.DataBaseAccessException;
import com.facebook.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Provides a service for authentication DAO
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class UserAuthenticationDAOImpl implements UserAuthenticationDAO {

    private static UserAuthenticationDAOImpl authenticationDAOImpl;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private UserAuthenticationDAOImpl() {
    }

    /**
     * <p>
     * Gets the instance of the authentication DAO
     * </p>
     *
     * @return Returns the instance of the authentication DAO
     */
    public static UserAuthenticationDAOImpl getInstance() {
        if(null == authenticationDAOImpl) {
            authenticationDAOImpl = new UserAuthenticationDAOImpl();
        }

        return authenticationDAOImpl;
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param user Represents the user details for getting information
     * @return true if the sign-up was successful, false otherwise.
     */
    public boolean registerUser(final User user)  {
        final String sql = "insert into sign(name, mobilenumber, email, password, dateofbirth ) values(?, ?, ?, ?, ?)";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getMobileNumber());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getDateOfBirth());
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
     * {@inheritDoc}
     * </p>
     *
     * @param user Refers {@link User}user to authenticated
     * @return Returns true if the user is successfully authenticated, otherwise false
     */
    @Override
    public boolean authenticateUser(final User user) {
        return (null != user.getEmail() ? isUserEmailExists(user) : isUserMobileNumberExists(user));
    }

    /**
     * <p>
     * Checks the users mobile number and password exists in the database
     * </p>
     *
     * @param user Refers {@link User} that user object contains the mobile number and password
     * @return Returns true if a user with the given mobile number and password is found, otherwise false
     */
    public boolean isUserMobileNumberExists(final User user) {
        final String sql = "select id,name,mobilenumber,email,password,dateofbirth from sign where phonenumber = ? and password = ?;";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getMobileNumber());
            preparedStatement.setString(2, user.getPassword());
            final ResultSet resultSet = preparedStatement.executeQuery();

            connection.commit();

            return resultSet.next();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    /**
     * <p>
     * Checks the users email and password exists in the database
     * </p>
     *
     * @param user Refers {@link User}user object contains the mobile number and password
     * @return Returns true if a user with the given mobile number and password is found, otherwise false
     */
    public boolean isUserEmailExists(final User user) {
        final String sql = "select id,name,mobilenumber,email,password,dateofbirth from sign where email = ? and password = ?";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            final ResultSet resultSet = preparedStatement.executeQuery();

            connection.commit();

            return resultSet.next();
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
     * @param user Refers {@link User}that user to retrieve the id
     * @return Returns the id of the user if found, otherwise null
     */
    @Override
    public Long getUserId(final User user) {
        final String sql = "select id from sign where phonenumber = ? or email = ?";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getMobileNumber());
            preparedStatement.setString(2, user.getEmail());
            final ResultSet resultSet = preparedStatement.executeQuery();

            connection.commit();

            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (SQLException exception) {
            throw new DataBaseAccessException("Problem in connection, check it out");
        }

        return null;
    }
}
