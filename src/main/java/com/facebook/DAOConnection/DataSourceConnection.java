package com.facebook.DAOConnection;

import com.facebook.customException.DataBaseAccessException;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>
 * Provides a database connection using the postgreSQL driver
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
     * @return Returns datasource connection object
     */
    public static DataSource getDataSource() {
        try {
            final InputStream inputStream = DataSourceConnection.class.getClassLoader().getResourceAsStream("db.properties");
            final BasicDataSource dataSource = new BasicDataSource();
            final Properties properties = new Properties();

            properties.load(inputStream);
            dataSource.setDriverClassName(properties.getProperty("DRIVER"));
            dataSource.setUrl(properties.getProperty("SQL_URL"));
            dataSource.setUsername(properties.getProperty("USER_NAME"));
            dataSource.setPassword(properties.getProperty("PASSWORD"));
            dataSource.setMinIdle(10);
            dataSource.setMaxIdle(100);

            return dataSource;
        } catch (IOException exception) {
            throw new DataBaseAccessException("Problem in Database connection, check it out");
        }
    }
}
