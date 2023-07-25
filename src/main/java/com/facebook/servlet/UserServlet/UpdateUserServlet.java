package com.facebook.servlet.UserServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.controller.UserController;
import com.facebook.model.User;
import com.facebook.servlet.CommonReader;
import org.json.JSONObject;

/**
 * <p>
 * Handle HTTP requests for the "/updateUser" url
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {

    private static final CommonReader commonReader = CommonReader.getInstance();
    private static final UserController userController = UserController.getInstance();

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
}

