package com.facebook.service;

import com.facebook.model.Like;
import com.facebook.model.User;

import java.util.Collection;

/**
 * <p>
 * Provides a service for managing like details
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public interface LikeService {

    /**
     * <p>
     * Checks the like is created
     * </p>
     *
     * @param like Reference {@link Like} that like to be created
     * @return True if the like is created, otherwise false
     */
    boolean create(final Like like);

    /**
     * <p>
     * Retrieves the like collection of the user
     * </p>
     *
     * @param postId Refers the post id to retrieves like details
     * @return Returns collection of likes for the user
     */
    Collection<User> get(final Long postId);

    /**
     * <p>
     * Retrieves the like count for the post
     * </p>
     *
     * @param postId Represents the post id to get the like count
     * @return Returns the like count of the post
     */
    Long getCount(final Long postId);

    /**
     * <p>
     * Deletes the like for the post with the id
     * </p>
     *
     * @param likeId Refers the id for unlike the post
     * @return Returns true if the like is unliked, otherwise false
     */
    boolean delete(final Long likeId);
}
