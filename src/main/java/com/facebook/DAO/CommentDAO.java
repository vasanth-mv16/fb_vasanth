package com.facebook.DAO;

import com.facebook.model.Comment;

/**
 * <p>
 * Provides the service for the comment DAO
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
     * @param comment Refer {@link Comment} to create
     * @return boolean - True if the comment is created, otherwise false
     */
    boolean create(final Comment comment);

    /**
     * <p>
     * Deletes the comment for post by the id
     * </p>
     *
     * @param id Refers the id for delete the comment
     * @return True if the comment is deleted, otherwise false
     */
    boolean delete(final Long id);
}
