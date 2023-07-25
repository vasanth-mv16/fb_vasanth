package com.facebook.servlet;

import com.facebook.controller.AuthenticationController;
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
 * Handles request and response for the user register and login
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
@WebServlet("/RegisterOrLogin")
public class AuthenticationServlet extends HttpServlet {

    private final CommonReader commonReader;
    private final AuthenticationController authenticationController;

    public AuthenticationServlet() {
        this.commonReader = CommonReader.getInstance();
        this.authenticationController = AuthenticationController.getInstance();
    }

    /**
     * <p>
     * Handles request and response for the user Registration
     * </p>
     *
     * @param request  Refer HTTP request object contains client request data
     * @param response Refer HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonObject = new JSONObject(jsonString);
        final UserBuilder user = new UserBuilder();

        user.setName(jsonObject.getString("name"));
        user.setEmail(jsonObject.getString("email"));
        user.setMobileNumber(jsonObject.getString("mobileNumber"));
        user.setPassword(jsonObject.getString("password"));
        user.setDateOfBirth(jsonObject.getString("dateOfBirth"));

        final JSONObject jsonResponse = new JSONObject();

        jsonResponse.put("message", authenticationController.registerUser(user.build()) ? "Sign Up Successfully" : "Sign Up failed");

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }

    /**
     * <p>
     * Handles request and response for the user authentication
     * </p>
     *
     * @param request  Refer HTTP request object contains client request data
     * @param response Refer HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonData = new JSONObject(jsonString);
        final UserBuilder user = new UserBuilder();

        user.setEmail(jsonData.getString("email"));
        user.setMobileNumber(jsonData.getString("mobileNumber"));
        user.setPassword(jsonData.getString("password"));

        final JSONObject jsonResponse = new JSONObject();

        jsonResponse.put("Message", authenticationController.authenticateUser(user.build()) ? "Sign in successfully" : "Sign in failed");

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }
}
