package com.facebook.servlet;

import com.facebook.controller.LikeController;
import com.facebook.model.Like;
import com.facebook.model.LikeBuilder;
import com.facebook.model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * <p>
 * Handles request and response for Like creation and deletion
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
        this.commonReader = CommonReader.getInstance();
        this.likeController = LikeController.getInstance();
    }

    /**
     * <p>
     * Handles request and response for the like creation
     * </p>
     *
     * @param request  Refer HTTP request object contains client request data
     * @param response Refer HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonData = new JSONObject(jsonString);
        final LikeBuilder like = new LikeBuilder();

        like.setPostId(jsonData.getLong("postid"));
        like.setUserId(jsonData.getLong("userid"));

        final JSONObject jsonResponse = new JSONObject();

        jsonResponse.put("message", likeController.create(like.build()) ? "Liked Successfully" : "Unable to Like");
        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }

    /**
     * <p>
     * Handles request and response for the like deletion
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

        jsonResponse.put("message", likeController.delete(jsonData.getLong("id")) ? "Post Unliked successfully" :
                "Post not Unliked");
        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }

    /**
     * <p>
     * Handles request and response for the like retrieving by user
     * </p>
     *
     * @param request  Refer HTTP request object contains client request data
     * @param response Refer HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final JSONObject jsonResponse = new JSONObject();
        final PrintWriter out = response.getWriter();
        final Like like = new Like();
        final Collection<User> users = likeController.get(like.getPostId());

        if (null != users) {

            final JSONArray jsonArray = new JSONArray();

            for (final User user : users) {
                final JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", user.getId());
                jsonObject.put("name", user.getName());
                jsonObject.put("mobileNumber", user.getMobileNumber());
                jsonObject.put("email", user.getEmail());
                jsonObject.put("password", user.getPassword());
                jsonObject.put("dateOfBirth", user.getDateOfBirth());
                jsonArray.put(jsonObject);
            }
            out.println(jsonArray);

            return;
        }
        jsonResponse.put("message", "Post Not Found");
        out.println(jsonResponse);
    }
}
