package com.facebook.servlet;

import com.facebook.controller.PostController;
import com.facebook.model.Post;
import com.facebook.model.PostBuilder;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * <p>
 *  Handles request and response for the "/post" url
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
@WebServlet("/post")
public class PostServlet extends HttpServlet {

    private final CommonReader commonReader;
    private final PostController postController;

    public PostServlet() {
        commonReader = CommonReader.getInstance();
        postController = PostController.getInstance();
    }

    /**
     * <p>
     * Handles request and response for the post creation
     * </p>
     *
     * @param request HTTP request object containing client request information
     * @param response HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonData = new JSONObject(jsonString);
        final PostBuilder post = new PostBuilder();
        final Timestamp uploadTime = Timestamp.from(Instant.now());

        post.setUserId(jsonData.getLong("userId"));
        post.setCaption(jsonData.getString("caption"));
        post.setLocation(jsonData.getString("location"));
        post.setUploadTime(uploadTime);

        final JSONObject jsonResponse = new JSONObject();

        if (postController.create(post.bulidPost())) {
            jsonResponse.put("message", "Post Successfully");
        } else {
            jsonResponse.put("message", "Post failed");
        }

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);

    }

    /**
     * <p>
     * Handles request and response for the get post
     * </p>
     *
     * @param request HTTP request object containing client request information
     * @param response HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonData = new JSONObject(jsonString);
        final Post post = postController.get(jsonData.getLong("id"));

        System.out.println("UserID : " + post.getUserId());
        System.out.println("Caption : " +post.getCaption());
        System.out.println("Location : " +post.getLocation());
        System.out.println("Upload Time : " + post.getUploadTime());

        final JSONObject jsonResponse = new JSONObject();

        if (null != postController.get(jsonData.getLong("id"))) {
            jsonResponse.put("message", "Get successfully");
        } else {
            jsonResponse.put("message", "Not found");
        }

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }

    /**
     * <p>
     * Handles request and response for the post updation
     * </p>
     *
     * @param request HTTP request object containing client request information
     * @param response HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    public void doPut(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonData = new JSONObject(jsonString);
        final Post post = postController.get(jsonData.getLong("id"));
        final Timestamp uploadTime = Timestamp.from(Instant.now());

        post.setCaption(jsonData.getString("caption"));
        post.setLocation(jsonData.getString("location"));
        post.setUploadTime(uploadTime);

        final JSONObject jsonResponse = new JSONObject();

        if (postController.update(post, jsonData.getLong("id"))) {
            jsonResponse.put("message", "Updated Successfully");
        } else {
            jsonResponse.put("message", "Updated failed");
        }

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }

    /**
     * <p>
     * Handles request and response for the post deletion
     * </p>
     *
     * @param request HTTP request object containing client request information
     * @param response HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    public void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonObject = new JSONObject(jsonString);
        final JSONObject jsonResponse = new JSONObject();

        if (postController.delete(jsonObject.getLong("id"))) {
            jsonResponse.put("message", "Post deleted successfully");
        } else {
            jsonResponse.put("message", "Post not found");
        }

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }
}
