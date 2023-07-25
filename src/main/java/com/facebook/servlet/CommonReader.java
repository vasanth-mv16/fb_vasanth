package com.facebook.servlet;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * <p>
 * Manages the generic method for json object
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class CommonReader {

    private static CommonReader commonReader;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    public CommonReader() {
    }

    /**
     * <p>
     * Gets the instance of Common reader
     * </p>
     *
     * @return Returns the instance of the common reader class
     */
    public static CommonReader getInstance() {
        if (null == commonReader) {
            commonReader = new CommonReader();
        }

        return  commonReader;
    }

    /**
     * <p>
     * Reads Json data from the request body and returns it as a string
     * </p>
     *
     * @param request HttpServletRequest object gets the incoming request
     * @return String contains the Json data read from the request body
     * @throws IOException File that doesn't exist at specified location
     */
    public String readJsonData(final HttpServletRequest request) throws IOException {
        final StringBuilder sb = new StringBuilder();
        String line;

        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }
}
