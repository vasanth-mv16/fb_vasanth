package com.facebook.DAO;

import com.facebook.model.Like;

import java.util.Collection;

/**
 * <p>
 * Provides the service for the like DAO
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public interface LikeDAO {

    /**
     * <p>
     * Checks the like is created
     * </p>
     *
     * @param like Refer {@link Like} to create
     * @return True if the like is created, otherwise false
     */
    boolean create(final Like like);

    /**
     * <p>
     * Gets the like list details
     * </p>
     *
     * @param userId Refers the userId to get like
     * @return Collection of likes
     */
    Collection<Like> get(final Long userId);

    /**
     * <p>
     * Gets the like count for the user
     * </p>
     *
     * @param postId Represents the user id to get the like count
     * @return Gets the like count of the user
     */
    Long getCount(final Long postId);

    /**
     * <p>
     * Deletes the like for post by the id
     * </p>
     *
     * @param likeId Refers the id for unlike the post
     * @return True if the like is unliked, otherwise false
     */
    boolean delete(final Long likeId);
}
