package com.facebook.servlet.UserServlet;

import com.facebook.controller.AuthenticationController;
import com.facebook.model.User;
import com.facebook.servlet.CommonReader;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * <p>
 * Handle HTTP requests for the "/signIn" url
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
@WebServlet ("/signIn")
public class SignInServlet extends HttpServlet {

    private static final CommonReader commonReader = CommonReader.getInstance();
    private static final AuthenticationController authenticationController = AuthenticationController.getInstance();

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
        final User user = new User();

        user.setEmail(jsonData.getString("email"));
        user.setMobileNumber(jsonData.getString("mobileNumber"));
        user.setPassword(jsonData.getString("password"));

        final JSONObject jsonResponse = new JSONObject();

        if (authenticationController.signIn(user)) {
            jsonResponse.put("Message", "Sign in successfully");
        } else {
            jsonResponse.put("Message", "Sign in failed");
        }

        response.setContentType("application/json");
        final PrintWriter out = response.getWriter();
        out.print(jsonResponse);
    }
}
