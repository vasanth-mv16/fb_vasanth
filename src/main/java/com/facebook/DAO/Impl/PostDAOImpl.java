package com.facebook.DAO.Impl;

import com.facebook.DAO.PostDAO;
import com.facebook.DAOConnection.DataSourceConnection;
import com.facebook.customException.DataBaseAccessException;
import com.facebook.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * <p>
 * Provides a following services for post
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class PostDAOImpl implements PostDAO {

    private static PostDAO postDAOImpl;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private PostDAOImpl() {
    }

    /**
     * <p>
     * Gets the instance of post service implementation
     * </p>
     *
     * @return Returns the singleton instance of the post service implementation class.
     */
    public static PostDAO getInstance() {
        if (null == postDAOImpl) {
            postDAOImpl = new PostDAOImpl();
        }

        return postDAOImpl;
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param posts Reference {@link Post} that post to create and insert
     * @return Returns true, if the post was created and inserted successfully, otherwise false
     */
    @Override
    public boolean create(final Post posts) {
        final String sql = "insert into user_post(userid, caption, location, uploadtime) values (?,?,?,?);";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, posts.getUserId());
            preparedStatement.setString(2, posts.getCaption());
            preparedStatement.setString(3, posts.getLocation());
            preparedStatement.setTimestamp(4,posts.getUploadTime());
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
     * @param user_id Refers the user id to retrieve the posts
     * @return Return collection of {@link Post} objects with the user id
     */
    @Override
    public Collection<Post> getAll(final Long user_id) {
        final Collection<Post> posts = new ArrayList<>();
        final String sql = "select id,userid,caption,location,uploadtime from user_post where userid = ?";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, user_id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                final Post post = new Post();

                post.setUserId(resultSet.getLong("userid"));
                post.setId(resultSet.getLong("id"));
                post.setCaption(resultSet.getString("caption"));
                post.setLocation(resultSet.getString("location"));
                post.setUploadTime(resultSet.getTimestamp("uploadtime"));
                posts.add(post);
            }
            connection.commit();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return posts;
    }

    /**
     * <p>
     * Retrieves posts with the post id from the database
     * </p>
     *
     * @param id Refers the id of the user to retrieve the posts
     * @return Return {@link Post}for the specified user
     */
    @Override
    public Post get(final Long id) {
        final String sql = "select id,userid,caption,location,uploadtime from user_post where id = ?";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setLong(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                final Post post = new Post();

                post.setUserId(resultSet.getLong("userid"));
                post.setId(resultSet.getLong("id"));
                post.setCaption(resultSet.getString("caption"));
                post.setLocation(resultSet.getString("location"));

                connection.commit();

                return post;
            }
        } catch (SQLException exception) {
            throw new DataBaseAccessException(exception.getMessage());
        }

        return null;
    }

    /**
     * <p>
     * {@inheritDoc}
     * </p>
     *
     * @param post Reference {@link Post} that post to be updated
     * @param id Refers to the id used to post the update
     * @return Returns true if the post is successfully updated, false otherwise
     */
    @Override
    public boolean update(final Post post, final Long id) {
        final String sql = "update user_post set caption = ?, location = ? where id = ? AND user_id = ?";

        try (final Connection connection = DataSourceConnection.getDataSource().getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false);
            preparedStatement.setString(1, post.getCaption());
            preparedStatement.setString(2, post.getLocation());
            preparedStatement.setLong(3, id);
            preparedStatement.setLong(4, post.getUserId());
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
     * Deletes a post from the database based on the post id
     * </p>
     *
     * @param id Refers the id for delete the post
     * @return Returns true if the post is successfully updated, false otherwise
     */
    @Override
    public boolean delete(final Long id) {
        final String sql = "delete from user_post where id = ?";

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
