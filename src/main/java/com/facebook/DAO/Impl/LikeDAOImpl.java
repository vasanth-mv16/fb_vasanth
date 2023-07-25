package com.facebook.DAO.Impl;

import com.facebook.DAO.LikeDAO;
import com.facebook.DAOConnection.DataSourceConnection;
import com.facebook.customException.DataBaseAccessException;
import com.facebook.model.Like;
import com.facebook.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 * Provides a Service for the like DAO
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class LikeDAOImpl implements LikeDAO {

    private static LikeDAO likeDAOImpl;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private LikeDAOImpl() {
    }

    /**
     * <p>
     * Gets the instance of like service implementation
     * </p>
     *
     * @return Returns instance of the like service implementation class.
     */
    public static LikeDAO getInstance() {
        if (null == likeDAOImpl) {
            likeDAOImpl = new LikeDAOImpl();
        }

        return likeDAOImpl;
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param like Refer {@link Like} that like to be created
     * @return Returns true if the like is created, otherwise false
     */
    @Override
    public boolean create(final Like like) {
        final String sql = "insert into like_post(user_id, post_id) values (?,?);";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, like.getUserId());
            preparedStatement.setLong(2, like.getPostId());
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
     * @param postId Refers the post id to retrieves like details
     * @return Returns collection of likes for the user
     */
    @Override
    public Collection<User> get(final Long postId) {
        final Collection<User> users = new ArrayList<>();
        final String sql = "select s.name from sign as s inner join like_post as lp on s.id = lp.userid where lp.postid = ?";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, postId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final User user = new User();

                user.setName(resultSet.getString("name"));
                users.add(user);
                connection.commit();
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return users;
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param postId Represents the post id to get the like count
     * @return Returns the like count for the specified post
     */
    @Override
    public Long getCount(final Long postId) {
        final String sql = "select post_id, count(post_id) as post_count from like_post where post_id = ? group by post_id;";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, postId);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final Like like = new Like();

                like.setPostId(resultSet.getLong("post_id"));
            }
            connection.commit();
        } catch (SQLException exception) {
            throw new DataBaseAccessException(exception.getMessage());
        }

        return postId;
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param likeId Refers the id for unlike the post
     * @return Returns true if the like is unliked, otherwise false
     */
    @Override
    public boolean delete(final Long likeId) {
        final String sql = "delete from like_post where id = ?";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, likeId);
            preparedStatement.executeUpdate();
            connection.commit();

            return true;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return false;
    }
}
