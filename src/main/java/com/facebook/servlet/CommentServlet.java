package com.facebook.servlet;

import com.facebook.controller.CommentController;
import com.facebook.model.Comment;
import com.facebook.model.CommentBuilder;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * Handles request and response for the comment creation and deletion
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
        this.commonReader = CommonReader.getInstance();
        this.commentController = CommentController.getInstance();
    }

    /**
     * <p>
     * Handles request and response for the comment creation
     * </p>
     *
     * @param request  Refer HTTP request object contains client request data
     * @param response Refer HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonData = new JSONObject(jsonString);
        final CommentBuilder comment = new CommentBuilder();

        comment.setPostId(jsonData.getLong("postid"));
        comment.setUserId(jsonData.getLong("userid"));

        final JSONObject jsonResponse = new JSONObject();

        jsonResponse.put("message", commentController.create(comment.build()) ? "Commented Successfully" : "Unable to comment");
        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }

    /**
     * <p>
     * Handles request and response for the comment deletion
     * </p>
     *
     * @param request  Refer HTTP request object contains client request data
     * @param response Refer HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonData = new JSONObject(jsonString);
        final JSONObject jsonResponse = new JSONObject();

        jsonResponse.put("message", commentController.delete(jsonData.getLong("id")) ? "Comment deleted " +
                "successfully" : "Failed to delete");
        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }
}
