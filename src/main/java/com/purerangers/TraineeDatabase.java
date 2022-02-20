package com.purerangers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static com.purerangers.SimLogger.logger;

public class TraineeDatabase {
    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/zzz";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12pass34";

    // init connection object
    private static Connection connection;
    // init properties object
    private static Properties properties;

    // create properties
    private static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }

    // connect database
    public static Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
                logger.info("Successfully connected to database.");
            } catch (ClassNotFoundException | SQLException e) {
                logger.error("Error connecting to database.");
            }
        }
        return connection;
    }

    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                logger.info("Successfully closed connection to database.");
            } catch (SQLException e) {
                logger.error("Error closing connection to database.");
            }
        }
    }
}
