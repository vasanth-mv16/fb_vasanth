package com.facebook.DAOConnection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * <p>
 * Provides a static method to retrieve a database connection using the postgreSQL driver
 * </p>
 *
 * @author vasanth
 */
public class JDConnection {

    private static JDConnection jdConnection;

    /**
     * <p>
     * Default constructor for JDConnection
     * </p>
     */
    private JDConnection() {
    }

    /**
     * <p>
     * Gets the instance of JDBC connection service
     * </p>
     *
     * @return Returns the singleton instance of the JDBC connection class.
     */
    public static JDConnection getInstance() {
        if (null == jdConnection) {
            jdConnection = new JDConnection();
        }

        return jdConnection;
    }

    /**
     * <p>
     * Retrieves a database connection using the postgreSQL driver
     * </p>
     *
     * @return The database connection object.
     */
    public static Connection getConnection() {
        Connection connection = null;

        try {
            final String filePath = "C:\\Users\\maria\\Downloads\\facebooks (3)\\facebooks\\facebooks\\DataBase_Resource\\jdbc.properties";
            final FileInputStream fileInputStream = new FileInputStream(filePath);
            final Properties properties = new Properties();

            properties.load(fileInputStream);
            final String SQL_URL = properties.getProperty("SQL_URL");
            final String USER_NAME = properties.getProperty("USER_NAME");
            final String PASSWORD = properties.getProperty("PASSWORD");
            connection = DriverManager.getConnection(SQL_URL, USER_NAME, PASSWORD);

        } catch (final Exception e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }
}
