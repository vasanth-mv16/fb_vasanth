package com.facebook.service;

import com.facebook.model.Post;

import java.util.Collection;

/**
 * <p>
 * Provides a service for manage the post
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public interface PostService {

    /**
     * <p>
     * Checks if the post details are created, and adds the post
     * </p>
     *
     * @param post Reference {@link Post} that post to be added
     * @return Returns true if the post is successfully added, false otherwise
     */
    boolean create(final Post post);

    /**
     * <p>
     * Retrieves the collection of post for the user
     * </p>
     *
     * @param userId Refers the user id to retrieve all the post
     * @return Returns the collection of posts for the specified user
     */
    Collection<Post> getAll(final Long userId);

    /**
     * <p>
     * Retrieves the post details using the post id
     * </p>
     *
     * @param id Represents the id of the post to retrieved
     * @return Returns {@link Post} details of the user
     */
    Post get(final Long id);

    /**
     * <p>
     * Checks if the post details are updated
     * </p>
     *
     * @param post Reference {@link Post} that post to be updated
     * @param id Refers the id to update the post details
     * @return Returns true if the post is successfully updated, false otherwise
     */
    boolean update(final Post post, final Long id);

    /**
     * <p>
     * Deletes the post details by using id
     * </p>
     *
     * @param id Refers the id for delete the post
     * @return Return true if the post is successfully updated, false otherwise
     */
    boolean delete(final Long id);
}
