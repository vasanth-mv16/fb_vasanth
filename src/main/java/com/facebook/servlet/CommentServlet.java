package com.facebook.servlet;

import com.facebook.controller.CommentController;
import com.facebook.model.Comment;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 *  Handles request and response for the "/comment" url
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
@WebServlet("/comment")
public class CommentServlet extends HttpServlet {

    private final CommonReader commonReader;
    private final CommentController commentController;

    public CommentServlet() {
        commonReader = CommonReader.getInstance();
        commentController = CommentController.getInstance();
    }

    /**
     * <p>
     * Handles request and response for the comment creation
     * </p>
     *
     * @param request HTTP request object containing client request information
     * @param response HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonData = new JSONObject(jsonString);
        final Comment comment = new Comment() ;

        comment.setPostId(jsonData.getLong("postid"));
        comment.setUserId(jsonData.getLong("userid"));

        final JSONObject jsonResponse = new JSONObject();

        if (commentController.create(comment)) {
            jsonResponse.put("message", "Commented Successfully");
        } else {
            jsonResponse.put("message", "Unable to comment");
        }

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);

    }

    /**
     * <p>
     * Handles request and response for the comment deletion
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

        if (commentController.delete(jsonData.getLong("id"))) {
            jsonResponse.put("message", "Post Unliked successfully");
        } else {
            jsonResponse.put("message", "Post not Unliked");
        }

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }
}
