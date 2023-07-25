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
 * Handles post creation and editing requests and responses
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
        this.commonReader = CommonReader.getInstance();
        this.postController = PostController.getInstance();
    }

    /**
     * <p>
     * Handles request and response for the post creation
     * </p>
     *
     * @param request  Refer HTTP request object contains client request data
     * @param response Refer HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonData = new JSONObject(jsonString);
        final PostBuilder post = new PostBuilder();
        final Timestamp uploadTime = Timestamp.from(Instant.now());

        post.setUserId(jsonData.getLong("userId"));
        post.setCaption(jsonData.getString("caption"));
        post.setLocation(jsonData.getString("location"));
        post.setUploadTime(uploadTime);

        final JSONObject jsonResponse = new JSONObject();

        jsonResponse.put("message", postController.create(post.buildPost()) ? "Post Successfully" : "Post failed");
        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }

    /**
     * <p>
     * Handles request and response for retrieve the post
     * </p>
     *
     * @param request  Refer HTTP request object contains client request data
     * @param response Refer HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonData = new JSONObject(jsonString);
        final Post post = postController.get(jsonData.getLong("id"));

        System.out.println("UserID : " + post.getUserId());
        System.out.println("Caption : " + post.getCaption());
        System.out.println("Location : " + post.getLocation());
        System.out.println("Upload Time : " + post.getUploadTime());

        final JSONObject jsonResponse = new JSONObject();

        jsonResponse.put("message", null != postController.get(jsonData.getLong("id")) ? "Retrieve successfully" : "Not found");
        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }

    /**
     * <p>
     * Handles post update requests and responses
     * </p>
     *
     * @param request  Refer HTTP request object contains client request data
     * @param response Refer HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    protected void doPut(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonData = new JSONObject(jsonString);
        final Post post = postController.get(jsonData.getLong("id"));
        final Timestamp uploadTime = Timestamp.from(Instant.now());

        post.setCaption(jsonData.getString("caption"));
        post.setLocation(jsonData.getString("location"));
        post.setUploadTime(uploadTime);

        final JSONObject jsonResponse = new JSONObject();

        jsonResponse.put("message", postController.update(post, jsonData.getLong("id")) ? "Updated Successfully" :
                "Updated failed");
        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }

    /**
     * <p>
     * Handles request and response for the post deletion
     * </p>
     *
     * @param request  Refer HTTP request object contains client request data
     * @param response Refer HTTP response object for sending data back to the clientq
     * @throws IOException File that doesn't exist at specified location
     */
    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonObject = new JSONObject(jsonString);
        final JSONObject jsonResponse = new JSONObject();

        jsonResponse.put("message", postController.delete(jsonObject.getLong("id")) ? "Post deleted successfully" :
                "Post not found");
        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }
}
