package com.facebook.service;

import com.facebook.model.Comment;

/**
 * <p>
 * Provides a service for manage comment details
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public interface CommentService {

    /**
     * <p>
     * Checks the comment is created
     * </p>
     *
     * @param comment Refer {@link Comment} that comment to be created
     * @return Returns true if the comment is created, otherwise false
     */
    boolean create(final Comment comment);

    /**
     * <p>
     * Deletes the comment for post by with id
     * </p>
     *
     * @param id Refers the id for delete the comment
     * @return Returns true if the comment is deleted, otherwise false
     */
    boolean delete(final Long id);
}
