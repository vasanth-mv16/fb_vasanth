package com.facebook.controller;

import com.facebook.model.Like;
import com.facebook.model.User;
import com.facebook.service.Impl2.LikeServiceImpl;
import com.facebook.service.LikeService;

import java.util.Collection;

/**
 * <p>
 * Given controller acts as request and respond for like
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class LikeController {

    private static LikeController likeController;
    private final LikeService likeDAOImpl;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private LikeController() {
        this.likeDAOImpl = new LikeServiceImpl();
    }

    /**
     * <p>
     * Gets the instance of the like controller
     * </p>
     *
     * @return Returns the instance of the like controller
     */
    public static LikeController getInstance() {
        if (null == likeController) {
            likeController = new LikeController();
        }

        return likeController;
    }

    /**
     * <p>
     * Checks the like is created
     * </p>
     *
     * @param like Reference {@link Like} that like to be created
     * @return True if the like is created, otherwise false
     */
    public boolean create(final Like like) {
        return likeDAOImpl.create(like);
    }

    /**
     * <p>
     * Retrieves the like collection of the user
     * </p>
     *
     * @param postId Refers the post id to retrieves like details
     * @return Returns collection of user
     */
    public Collection<User> get(final Long postId) {
        return likeDAOImpl.get(postId);
    }

    /**
     * <p>
     * Retrieves the like count for the post
     * </p>
     *
     * @param postId Represents the post id to get the like count
     * @return Returns the like count of the post
     */
    public Long getCount(final Long postId) {
        return likeDAOImpl.getCount(postId);
    }

    /**
     * <p>
     * Deletes the like for the post with the id
     * </p>
     *
     * @param likeId Refers the id for unlike the post
     * @return Returns true if the like is unliked, otherwise false
     */
    public boolean delete(final Long likeId) {
        return likeDAOImpl.delete(likeId);
    }
}
