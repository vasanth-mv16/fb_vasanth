package com.facebook.service.Impl2;

import com.facebook.DAO.Impl.LikeDAOImpl;
import com.facebook.DAO.LikeDAO;
import com.facebook.model.Like;
import com.facebook.model.User;
import com.facebook.service.LikeService;

import java.util.Collection;

/**
 * <p>
 * Implementation of the like service interface for managing likes
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class LikeServiceImpl implements LikeService {

    private final LikeDAO LIKE_DAO_IMPL = LikeDAOImpl.getInstance();

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param like Refer {@link Like} that like to be created
     * @return Returns true, if the like is created, otherwise false
     */
    @Override
    public boolean create(final Like like) {
        return LIKE_DAO_IMPL.create(like);
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param postId Refers the post id to retrieves like details
     * @return Returns collection of likes for the user
     */
    @Override
    public Collection<User> get(final Long postId) {
        return LIKE_DAO_IMPL.get(postId);
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param postId Represents the user id to get the like count
     * @return Returns the like count of the  specified user
     */
    @Override
    public Long getCount(final Long postId) {
        return LIKE_DAO_IMPL.getCount(postId);
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param likeId Refers the id for unlike the post
     * @return Returns true, if the like is unliked, otherwise false
     */
    @Override
    public boolean delete(final Long likeId) {
        return LIKE_DAO_IMPL.delete(likeId);
    }
}
