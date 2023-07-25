package com.facebook.controller;

import com.facebook.model.User;
import com.facebook.service.AuthenticationService;
import com.facebook.service.Impl2.AuthenticationServiceImpl;

/**
 * <p>
 * Given controller act us for request and respond for user authentication
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class AuthenticationController {

    private static AuthenticationController authenticationController;
    private final AuthenticationService authenticationServiceImpl;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private AuthenticationController() {
        this.authenticationServiceImpl = new AuthenticationServiceImpl();
    }

    /**
     * <p>
     * Gets the instance of the authentication controller
     * </p>
     *
     * @return Returns the instance of the authentication controller
     */
    public static AuthenticationController getInstance() {
        if (null == authenticationController) {
            authenticationController = new AuthenticationController();
        }

        return authenticationController;
    }

    /**
     * <p>
     * Checks the user to be register
     * </p>
     *
     * @param user Refers {@link User} has to created
     * @return True if the user is created, false otherwise
     */
    public boolean registerUser(final User user) {
        return authenticationServiceImpl.registerUser(user);
    }

    /**
     * <p>
     * Checks the user authentication
     * </p>
     *
     * @param user Refers {@link User} has to sign in
     * @return True if the user is sign in, false otherwise
     */
    public boolean authenticateUser(final User user) {
        return authenticationServiceImpl.authenticateUser(user);
    }

    /**
     * <p>
     * Retrieves the id of the user
     * </p>
     *
     * @param user Refers {@link User} to get the id
     * @return Return id of the user
     */
    public Long getUserId(final User user) {
        return authenticationServiceImpl.getUserId(user);
    }
}
