package com.facebook.controller;

import com.facebook.model.Post;
import com.facebook.service.Impl2.PostServiceImpl;
import com.facebook.service.PostService;

import java.util.Collection;

/**
 * <p>
 * Given controller acts as request and respond for post
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class PostController {

    private static PostController postController;
    private final PostService postServiceImpl;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private PostController() {
        this.postServiceImpl = new PostServiceImpl();
    }

    /**
     * <p>
     * Gets the instance of the post controller
     * </p>
     *
     * @return Returns the instance of the controller
     */
    public static PostController getInstance() {
        if (null == postController) {
            postController = new PostController();
        }

        return postController;
    }

    /**
     * <p>
     * Checks if the post details are created, and adds the post
     * </p>
     *
     * @param post Reference {@link Post} that post to be added
     * @return Returns true if the post is successfully added, false otherwise
     */
    public boolean create(final Post post) {
        return postServiceImpl.create(post);
    }

    /**
     * <p>
     * Retrieves the collection of post for the user
     * </p>
     *
     * @param userId Refers the user id to retrieve all the post
     * @return Returns the collection of posts for the specified user
     */
    public Collection<Post> getALl(final Long userId) {
        return postServiceImpl.getAll(userId);
    }

    /**
     * <p>
     * Retrieves the post details using the post id
     * </p>
     *
     * @param id Represents the id of the post to retrieved
     * @return Returns {@link Post} details of the user
     */
    public Post get(final Long id) {
        return postServiceImpl.get(id);
    }

    /**
     * <p>
     * Checks if the post details are updated
     * </p>
     *
     * @param post Reference {@link Post} that post to be updated
     * @param id Refers the id to update the post details
     * @return Returns true if the post is successfully updated, false otherwise
     */
    public boolean update(final Post post, final Long id) {
        return postServiceImpl.update(post, id);
    }

    /**
     * <p>
     * Deletes the post details by using id
     * </p>
     *
     * @param id Refers the id for delete the post
     * @return Return true if the post is successfully updated, false otherwise
     */
    public boolean delete(final Long id) {
        return postServiceImpl.delete(id);
    }
}
