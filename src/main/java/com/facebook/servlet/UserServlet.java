package com.facebook.servlet;

import com.facebook.controller.AuthenticationController;
import com.facebook.controller.UserController;
import com.facebook.model.User;
import com.facebook.model.UserBuilder;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 *  Handles request and response for the "/user" url
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private final CommonReader commonReader;
    private final AuthenticationController authenticationController;
    private final UserController userController;

    public UserServlet() {
        commonReader = CommonReader.getInstance();
        authenticationController = AuthenticationController.getInstance();
        userController = UserController.getInstance();

    }

    /**
     * <p>
     * Handles request and response for the user creation
     * </p>
     *
     * @param request HTTP request object containing client request information
     * @param response HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonObject = new JSONObject(jsonString);
        final UserBuilder user = new UserBuilder();

        user.setName(jsonObject.getString("name"));
        user.setEmail(jsonObject.getString("email"));
        user.setMobileNumber(jsonObject.getString("mobileNumber"));
        user.setPassword(jsonObject.getString("password"));
        user.setDateOfBirth(jsonObject.getString("dateOfBirth"));

        final JSONObject jsonResponse = new JSONObject();

        if (authenticationController.signUp(user.build())) {
            jsonResponse.put("message", "Sign Up Successfully");
        } else {
            jsonResponse.put("message", "Sign Up failed");
        }

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }

    /**
     * <p>
     * Handles request and response for the user login
     * </p>
     *
     * @param request HTTP request object containing client request information
     * @param response HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonData = new JSONObject(jsonString);
        final UserBuilder user = new UserBuilder();

        user.setEmail(jsonData.getString("email"));
        user.setMobileNumber(jsonData.getString("mobileNumber"));
        user.setPassword(jsonData.getString("password"));

        final JSONObject jsonResponse = new JSONObject();

        if (authenticationController.signIn(user.build())) {
            jsonResponse.put("Message", "Sign in successfully");
        } else {
            jsonResponse.put("Message", "Sign in failed");
        }

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }

    /**
     * <p>
     * Handles request and response for the user updation
     * </p>
     *
     * @param request HTTP request object containing client request information
     * @param response HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    @Override
    public void doPut(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonObject = new JSONObject(jsonString);
        final User user = userController.get(jsonObject.getLong("id"));

        user.setName(jsonObject.getString("name"));
        user.setEmail(jsonObject.getString("email"));
        user.setMobileNumber(jsonObject.getString("mobileNumber"));
        user.setPassword(jsonObject.getString("password"));
        user.setDateOfBirth(jsonObject.getString("dateOfBirth"));

        final JSONObject jsonResponse = new JSONObject();

        if (userController.update(user, jsonObject.getLong("id"))) {
            jsonResponse.put("message", "User details updated successfully");
        } else {
            jsonResponse.put("message", "User not found or no changes made");
        }

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }

    /**
     * <p>
     * Handles request and response for the user deletion
     * </p>
     *
     * @param request HTTP request object containing client request information
     * @param response HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    @Override
    public void doDelete(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonObject = new JSONObject(jsonString);
        final JSONObject jsonResponse = new JSONObject();

        if (userController.delete(jsonObject.getLong("id"))) {
            jsonResponse.put("message", "User deleted successfully");
        } else {
            jsonResponse.put("message", "User not found");
        }

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }
}
