package com.facebook.service.Impl2;

import com.facebook.DAO.CommentDAO;
import com.facebook.DAO.Impl.CommentDAOImpl;
import com.facebook.model.Comment;
import com.facebook.service.CommentService;

/**
 * <p>
 * Implementation of the comment service interface for managing comments
 * </p>
 *
 * @author vasanth
 * @version 1.0
 */
public class CommentServiceImpl implements CommentService {

    private final CommentDAO COMMENT_DAO_IMPL = CommentDAOImpl.getInstance();

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param comment Refer {@link Comment} that comment to be created
     * @return Returns true, if the comment is created, otherwise false
     */
    @Override
    public boolean create(final Comment comment) {
        return COMMENT_DAO_IMPL.create(comment);
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param id Refers the id for delete the comment
     * @return Returns true, if the comment is deleted, otherwise false
     */
    @Override
    public boolean delete(final Long id) {
        return COMMENT_DAO_IMPL.delete(id);
    }
}
