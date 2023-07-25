package com.facebook.DAO;

import com.facebook.model.User;

/**
 * <p>
 * Provides a service for the user's information
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public interface UserDAO {

    /**
     * <p>
     * Updates user details
     * </p>
     *
     * @param user Reference {@link User} that user to be updated
     * @param id Refers the id for the updation
     * @return Returns true if the user information has been updated, false otherwise
     */
    boolean update(final User user, final Long id);

    /**
     * <p>
     * Deletes user details
     * </p>
     *
     * @param id Represents the id of the user to be deleted
     * @return Returns true if the user information has been deleted, false otherwise
     */
    boolean delete(final Long id);

    /**
     * <p>
     * Retrieves a user by id
     * </p>
     *
     * @param id Represents the id of the user to retrieve
     * @return Returns {@link User} details for specified id
     */
    User get(final Long id);
}
