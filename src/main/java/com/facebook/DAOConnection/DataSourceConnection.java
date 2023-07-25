package com.facebook.DAOConnection;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * <p>
 *  Provides a database connection using the postgreSQL driver
 * </p>
 *
 * @author vasanth
 * @version 1.1
 */
public class DataSourceConnection {

    private static DataSourceConnection dataSourceConnection;

    /**
     * <p>
     * Enables the creation of only one object at a time
     * </p>
     */
    private DataSourceConnection() {
    }

    /**
     * <p>
     * Gets the instance of JDBC connection service
     * </p>
     *
     * @return Returns the instance of the JDBC connection class
     */
    public static DataSourceConnection getInstance() {
        if(null == dataSourceConnection) {
            dataSourceConnection = new DataSourceConnection();
        }
        return dataSourceConnection;
    }

    /**
     * <p>
     * Retrieves a connection using the postgreSQL driver
     * </p>
     *
     * @return Returns connection object
     */
    public static DataSource getDataSource() {
        try {
            final String filePath = "C:\\Users\\maria\\IdeaProjects\\maven_project\\facebook\\src\\test\\resources\\db.properties";
            final FileInputStream fileInputStream = new FileInputStream(filePath);
            final BasicDataSource dataSource = new BasicDataSource();
            final Properties properties = new Properties();

            properties.load(fileInputStream);

            dataSource.setDriverClassName(properties.getProperty("DRIVER"));
            dataSource.setUrl(properties.getProperty("SQL_URL"));
            dataSource.setUsername(properties.getProperty("USER_NAME"));
            dataSource.setPassword(properties.getProperty("PASSWORD"));
            dataSource.setMinIdle(10);
            dataSource.setMaxIdle(100);

            return dataSource;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return null;
    }
}
