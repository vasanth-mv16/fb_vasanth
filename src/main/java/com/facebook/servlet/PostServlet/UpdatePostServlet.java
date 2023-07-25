package com.facebook.servlet.PostServlet;

import com.facebook.controller.PostController;
import com.facebook.model.Post;
import com.facebook.servlet.CommonReader;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * <p>
 * Handles request and response for the "/updatePost" url
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
@WebServlet ("/updatePost")
public class UpdatePostServlet extends HttpServlet {

    private static final CommonReader commonReader = CommonReader.getInstance();
    private static final PostController postController = PostController.getInstance();

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
}
