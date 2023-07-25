package com.facebook.DAO.Impl;

import com.facebook.DAO.CommentDAO;
import com.facebook.DAOConnection.DataSourceConnection;
import com.facebook.model.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>
 * Provides a service for comment DAO
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class CommentDAOImpl implements CommentDAO {

    private static CommentDAO commentDAOImpl;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private CommentDAOImpl() {
    }

    /**
     * <p>
     * Gets the instance of comment service implementation
     * </p>
     *
     * @return Returns instance of the comment service implementation class.
     */
    public static CommentDAO getInstance() {
        if (null == commentDAOImpl) {
            commentDAOImpl = new CommentDAOImpl();
        }

        return commentDAOImpl;
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param comment Reference {@link Comment} that comment to be created
     * @return Returns true if the comment is created, otherwise false
     */
    @Override
    public boolean create(final Comment comment) {
        final String sql = "insert into comment_post(userid, postid, message) values (?,?,?);";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, comment.getUserId());
            preparedStatement.setLong(2, comment.getPostId());
            preparedStatement.setString(3, comment.getMessage());
            preparedStatement.executeUpdate();
            connection.commit();

            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param id Refers the id for delete the comment
     * @return Returns true if the comment is deleted, otherwise false
     */
    @Override
    public boolean delete(Long id) {
        final String sql = "delete from comment_post where id = ?";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();

            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }
}
