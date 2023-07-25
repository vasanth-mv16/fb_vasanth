package com.facebook.service.Impl2;

import com.facebook.DAO.Impl.UserDAOImpl;
import com.facebook.DAO.UserDAO;
import com.facebook.model.User;
import com.facebook.service.UserService;

/**
 * <p>
 * Provides methods to update, delete, and retrieve user details
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    private final UserDAO USER_DAO_IMPL = UserDAOImpl.getInstance();

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param user Reference {@link User} that user to be updated
     * @return Returns true if the user details are updated, false otherwise
     */
    @Override
    public boolean update(final User user, final Long id) {
        return USER_DAO_IMPL.update(user, id);
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param id Represents the id of the user to be deleted
     * @return Returns true if the user details are successfully deleted, false otherwise
     */
    @Override
    public boolean delete(final Long id) {
        return USER_DAO_IMPL.delete(id);
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param id Represents the id of the user to retrieve
     * @return Returns {@link User} details by the id
     */
    @Override
    public User get(final Long id) {
        return USER_DAO_IMPL.get(id);
    }
}
