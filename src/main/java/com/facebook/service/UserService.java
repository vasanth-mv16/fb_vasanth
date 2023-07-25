package com.facebook.service;

import com.facebook.model.User;

/**
 * <p>
 * Provides a service for the user edit details
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public interface UserService {

    /**
     * <p>
     * Updates user details
     * </p>
     *
     * @param user Refers {@link User} to update
     * @param id Refers id for the update the user details
     * @return Returns true if the user details are updated, false otherwise
     */
    boolean update(final User user, final Long id);

    /**
     * <p>
     * Deletes user details
     * </p>
     *
     * @param id Represents the id of the user to be deleted
     * @return Return true if the user details are successfully deleted, false otherwise
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

