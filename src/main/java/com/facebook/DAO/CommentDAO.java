package com.facebook.DAO;

import com.facebook.model.Comment;

/**
 * <p>
 * Provides a service for the comment DAO
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public interface CommentDAO {

    /**
     * <p>
     * Checks the comment is created
     * </p>
     *
     * @param comment Reference {@link Comment} that comment to be created
     * @return Returns true if the comment is created, otherwise false
     */
    boolean create(final Comment comment);

    /**
     * <p>
     * Deletes the comment for post by the id
     * </p>
     *
     * @param id Refers the id for delete the comment
     * @return Returns true if the comment is deleted, otherwise false
     */
    boolean delete(final Long id);
}
