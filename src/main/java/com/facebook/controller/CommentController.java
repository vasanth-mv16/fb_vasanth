package com.facebook.controller;

import com.facebook.model.Comment;
import com.facebook.service.CommentService;
import com.facebook.service.Impl2.CommentServiceImpl;

/**
 * <p>
 * Given comment controller acts as request and respond for comment
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class CommentController {

    private static CommentController commentController;
    private final CommentService commentDAO;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private CommentController() {
        this.commentDAO = new CommentServiceImpl();
    }

    /**
     * <p>
     * Retrieve the instance of the comment controller
     * </p>
     *
     * @return Returns the instance of the comment controller
     */
    public static CommentController getInstance() {
        if (null == commentController) {
            commentController = new CommentController();
        }
        return commentController;
    }

    /**
     * <p>
     * Checks the comment is created
     * </p>
     *
     * @param comment Refer {@link Comment} that comment to be created
     * @return Returns true if the comment is created, otherwise false
     */
    public boolean create(final Comment comment) {
        return commentDAO.create(comment);
    }

    /**
     * <p>
     * Deletes the comment for post by with id
     * </p>
     *
     * @param id Refers the id for delete the comment
     * @return Returns true if the comment is deleted, otherwise false
     */
    public boolean delete(final Long id) {
        return commentDAO.delete(id);
    }
}
