package com.facebook.servlet.PostServlet;

import com.facebook.controller.PostController;
import com.facebook.servlet.CommonReader;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * Handles request and response for the "/deletePost" url
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
@WebServlet("/deletePost")
public class DeletePostServlet extends HttpServlet {

    private static final CommonReader commonReader = CommonReader.getInstance();
    private static final PostController postController = PostController.getInstance();

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
