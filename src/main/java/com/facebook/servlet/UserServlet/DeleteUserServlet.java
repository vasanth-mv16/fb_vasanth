package com.facebook.servlet.UserServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.facebook.controller.UserController;
import com.facebook.servlet.CommonReader;
import org.json.JSONObject;

/**
 * <p>
 * Handles HTTP requests for the "/deleteUser" url
 * </p>
 *
 * @author vasanth
 * @version  1.1
 */
@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {

    private static final CommonReader commonReader = CommonReader.getInstance();
    private static final UserController userController = UserController.getInstance();

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
