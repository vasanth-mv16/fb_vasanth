package com.facebook.servlet;

import com.facebook.controller.PostController;
import com.facebook.model.Post;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * <p>
 * Handles request and response for the retrieve all post details
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
@WebServlet("/getAllPost")
public class GetAllPost extends HttpServlet {

    private final CommonReader commonReader;
    private final PostController postController;

    public GetAllPost() {
        this.commonReader = CommonReader.getInstance();
        this.postController = PostController.getInstance();
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
        final JSONObject jsonObject = new JSONObject(jsonString);
        final JSONObject jsonResponse = new JSONObject();
        final Collection<Post> posts = postController.getALl(jsonObject.getLong("userId"));
        final PrintWriter out = response.getWriter();

        if (!posts.isEmpty()) {
            final JSONArray jsonArray = new JSONArray();

            for (final Post post : posts) {
                final JSONObject jsonData = new JSONObject();

                jsonData.put("id", post.getId());
                jsonData.put("userId", post.getUserId());
                jsonData.put("caption", post.getCaption());
                jsonData.put("location", post.getLocation());
                jsonData.put("uploadTime", post.getUploadTime());
                jsonArray.put(jsonData);
            }
            out.println(jsonArray);

            return;
        }

        response.setContentType("application/json");
        jsonResponse.put("message", "No user found");
        out.println(jsonResponse);
    }
}
