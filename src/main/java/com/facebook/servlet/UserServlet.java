package com.facebook.servlet;

import com.facebook.controller.UserController;
import com.facebook.model.User;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * Handles request and response for the user edit details
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final CommonReader commonReader;
    private final UserController userController;

    public UserServlet() {
        this.commonReader = CommonReader.getInstance();
        this.userController = UserController.getInstance();
    }

    /**
     * <p>
     * Handles user update requests and responses
     * </p>
     *
     * @param request  Refer HTTP request object contains client request data
     * @param response Refer HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    @Override
    protected void doPut(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonObject = new JSONObject(jsonString);
        final User user = userController.get(jsonObject.getLong("id"));

        user.setName(jsonObject.getString("name"));
        user.setEmail(jsonObject.getString("email"));
        user.setMobileNumber(jsonObject.getString("mobileNumber"));
        user.setPassword(jsonObject.getString("password"));
        user.setDateOfBirth(jsonObject.getString("dateOfBirth"));

        final JSONObject jsonResponse = new JSONObject();

        jsonResponse.put("message", userController.update(user, jsonObject.getLong("id")) ? "User details updated" +
                " successfully" : "User not found or no changes made");

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }

    /**
     * <p>
     * Handles request and response for the user deletion
     * </p>
     *
     * @param request  Refer HTTP request object contains client request data
     * @param response Refer HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    @Override
    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonObject = new JSONObject(jsonString);
        final JSONObject jsonResponse = new JSONObject();

        jsonResponse.put("message", userController.delete(jsonObject.getLong("id")) ? "User deleted successfully" :
                "User not found");

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }
}
