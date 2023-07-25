package com.facebook.DAO;

import com.facebook.model.User;

/**
 * <p>
 * Provides a service DAO for user authentication
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public interface UserAuthenticationDAO {

    /**
     * <p>
     * Registers user information's in the database
     * </p>
     *
     * @param user Refers {@link User} has to be registered
     * @return Returns true if the user information has been register, false otherwise
     */
    boolean registerUser(final User user);

    /**
     * <p>
     * Validates user sign-in details in the database
     * </p>
     *
     * @param user Refer {@link User} has to be authenticated user
     * @return Returns true if the user information has been authenticated, false otherwise
     */
    boolean authenticateUser(final User user);

    /**
     * <p>
     * Retrieves user id from the database
     * </p>
     *
     * @param user Refers {@link User} to get the id
     * @return Returns the id of the user
     */
    Long getUserId(final User user);
}
