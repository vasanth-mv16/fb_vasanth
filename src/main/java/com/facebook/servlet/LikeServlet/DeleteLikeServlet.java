package com.facebook.servlet.LikeServlet;

import com.facebook.controller.LikeController;
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
 * Handles request and response the "/deleteLike" url
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
@WebServlet("/deleteLike")
public class DeleteLikeServlet extends HttpServlet {

    private static final CommonReader commonReader = CommonReader.getInstance();
    private static final LikeController likeController = LikeController.getInstance();

    /**
     * <p>
     * Handles request and response for the like deletion
     * </p>
     *
     * @param request HTTP request object containing client request information
     * @param response HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    public void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonData = new JSONObject(jsonString);
        final JSONObject jsonResponse = new JSONObject();

        if (likeController.delete(jsonData.getLong("id"))) {
            jsonResponse.put("message", "Post Unliked successfully");
        } else {
            jsonResponse.put("message", "Post not Unliked");
        }

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }
}
