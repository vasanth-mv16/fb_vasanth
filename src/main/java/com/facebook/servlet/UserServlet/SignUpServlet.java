package com.facebook.servlet.UserServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.controller.AuthenticationController;
import com.facebook.model.User;

import com.facebook.servlet.CommonReader;
import org.json.JSONObject;

/**
 * <p>
 * Handle HTTP requests for the "/signup" url
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    private static final CommonReader commonReader = CommonReader.getInstance();
    private static final AuthenticationController authenticationController = AuthenticationController.getInstance();

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
        final User user = new User();

        user.setName(jsonObject.getString("name"));
        user.setEmail(jsonObject.getString("email"));
        user.setMobileNumber(jsonObject.getString("mobileNumber"));
        user.setPassword(jsonObject.getString("password"));
        user.setDateOfBirth(jsonObject.getString("dateOfBirth"));

        final JSONObject jsonResponse = new JSONObject();

        if (authenticationController.signUp(user)) {
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
     * Handles request and response for the user sign up
     * </p>
     *
     * @param request HTTP request object containing client request information
     * @param response HTTP response object for sending data back to the client
     * @throws IOException File that doesn't exist at specified location
     */
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final String jsonString = commonReader.readJsonData(request);
        final JSONObject jsonObject = new JSONObject(jsonString);
        final User user = new User();

        user.setName(jsonObject.getString("name"));
        user.setEmail(jsonObject.getString("email"));
        user.setDateOfBirth(jsonObject.getString("dateOfBirth"));

        final JSONObject jsonResponse = new JSONObject();

        if (authenticationController.signUp(user)) {
            jsonResponse.put("message", "Sign Up Successfully");
            jsonResponse.put("userId", authenticationController.getUserId(user));
        }

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }
}

