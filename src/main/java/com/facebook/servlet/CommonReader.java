package com.facebook.servlet;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CommonReader {

    private static CommonReader commonReader;

    public CommonReader() {
    }

    public static CommonReader getInstance() {
        if (null == commonReader) {
            commonReader = new CommonReader();
        }

        return  commonReader;
    }

    public String readJsonData(HttpServletRequest request) throws IOException {
        final StringBuilder sb = new StringBuilder();
        String line;

        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }

        return sb.toString();
    }


}
