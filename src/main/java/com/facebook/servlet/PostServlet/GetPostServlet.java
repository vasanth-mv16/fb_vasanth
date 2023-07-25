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

/**
 * <p>
 * Handles request and response for the "/getPost" url
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
@WebServlet ("/getPost")
public class GetPostServlet extends HttpServlet {

    private static final CommonReader commonReader = CommonReader.getInstance();
    private static final PostController postController = PostController.getInstance();

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
}
