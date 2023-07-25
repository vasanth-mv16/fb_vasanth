package com.facebook.DAO;

import com.facebook.model.Post;

import java.util.Collection;

/**
 * <p>
 * Provides a service for the post DAO
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public interface PostDAO {

    /**
     * <p>
     * Checks if the post details are created
     * </p>
     *
     * @param post Refers {@link Post} that post to be added
     * @return Returns true if the post is successfully added, false otherwise
     */
    boolean create(final Post post);

    /**
     * <p>
     * Retrieves the collection of post details
     * </p>
     *
     * @param userId Refers the user id to retrieve all the post
     * @return Returns collection of posts for specified user
     */
    Collection<Post> getAll(final Long userId);

    /**
     * <p>
     * Retrieves the post details using the post id
     * </p>
     *
     * @param id Represents the id of the post to retrieve
     * @return Returns {@link Post} details of the user
     */
    Post get(final Long id);

    /**
     * <p>
     * Checks if the post details are updated
     * </p>
     *
     * @param post Reference {@link Post} that post to be updated
     * @param id Refers to the id used to post the update
     * @return Returns true if the post is successfully updated, false otherwise
     */
    boolean update(final Post post, final Long id);

    /**
     * <p>
     * Deletes the post details by using the id
     * </p>
     *
     * @param id Refers the id for delete the post
     * @return Returns true if the post is successfully updated, false otherwise
     */
    boolean delete(final Long id);
}
