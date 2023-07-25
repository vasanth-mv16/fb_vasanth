package com.facebook.servlet;

import com.facebook.controller.LikeController;
import com.facebook.model.Like;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 *  Handles request and response for the "/like" url
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
@WebServlet("/like")
public class LikeServlet extends HttpServlet {

    private final CommonReader commonReader;
    private final LikeController likeController;

    public LikeServlet() {
        commonReader = CommonReader.getInstance();
        likeController = LikeController.getInstance();
    }

    /**
     * <p>
     * Handles request and response for the Like creation
     * </p>
     *
     * @param request HTTP request object containing client request information
     * @param response HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonData = new JSONObject(jsonString);
        final Like like = new Like();

        like.setPostId(jsonData.getLong("postid"));
        like.setUserId(jsonData.getLong("userid"));

        final JSONObject jsonResponse = new JSONObject();

        if (likeController.create(like)) {
            jsonResponse.put("message", "Liked Successfully");
        } else {
            jsonResponse.put("message", "Unable to Like");
        }

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);

    }

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
