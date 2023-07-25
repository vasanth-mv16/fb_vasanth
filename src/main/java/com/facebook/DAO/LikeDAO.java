package com.facebook.DAO;

import com.facebook.model.Like;
import com.facebook.model.User;

import java.util.Collection;

/**
 * <p>
 * Provides a service for the like DAO
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
     * @param like Refer {@link Like} that like to be created
     * @return Returns true if the like is created, otherwise false
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
     * @return Returns the like count for the specified post
     */
    Long getCount(final Long postId);

    /**
     * <p>
     * Deletes the like for post by the id
     * </p>
     *
     * @param likeId Refers the id for unlike the post
     * @return Returns true if the like is unliked, otherwise false
     */
    boolean delete(final Long likeId);
}
