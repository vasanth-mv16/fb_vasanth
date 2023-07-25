package com.facebook.service.Impl2;

import com.facebook.DAO.Impl.PostDAOImpl;
import com.facebook.DAO.PostDAO;
import com.facebook.model.Post;
import com.facebook.service.PostService;

import java.util.Collection;

/**
 * <p>
 * Implementation of the PostService interface for managing posts
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class PostServiceImpl implements PostService {

    private final PostDAO POST_DAO_IMPL = PostDAOImpl.getInstance();

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param post Refers {@link Post} that post to be added
     * @return Returns true if the post is successfully added, false otherwise
     */
    @Override
    public boolean create(final Post post) {
        return POST_DAO_IMPL.create(post);
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param userId Refers the specified user to retrieve all the post
     * @return Returns the collection of posts for the specified user
     */
    @Override
    public Collection<Post> getAll(final Long userId) {
        return POST_DAO_IMPL.getAll(userId);
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param id Represents the id of the post to retrieve
     * @return Returns {@link Post} details of the user
     */
    @Override
    public Post get(final Long id) {
        return POST_DAO_IMPL.get(id);
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param post Refers {@link Post} to update
     * @param id Represents the id of the post to be updated
     * @return Returns true if the post is successfully updated, false otherwise
     */
    @Override
    public boolean update(final Post post, final Long id) {
        return POST_DAO_IMPL.update(post, id);
    }

    /**
     * <p>
     * Deletes the post details by passing id
     * </p>
     *
     * @param id Refers the id for delete the post
     * @return True if the post is successfully updated, false otherwise
     */
    @Override
    public boolean delete(final Long id) {
        return POST_DAO_IMPL.delete(id);
    }
}
