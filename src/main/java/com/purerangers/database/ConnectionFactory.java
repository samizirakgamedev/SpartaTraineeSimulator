package com.purerangers.database;

import com.purerangers.Simulation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;


public class ConnectionFactory
{
    private static ConnectionFactory instance;
    private boolean autoExpand;
    private Queue<Connection> connections = new LinkedList<>();

    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class.getName());

    private ConnectionFactory() {
        autoExpand = false;
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        }

        return instance;
    }

    public boolean getAutoExpand() {
        return autoExpand;
    }

    public void setAutoExpand(boolean autoExpand) {
        this.autoExpand = autoExpand;
    }

    private Connection getNewConnection() {
        try (InputStream inputStream = new FileInputStream("src/main/resources/mysql.properties")) {
            Properties properties = new Properties();
            properties.load(inputStream);

            return DriverManager.getConnection(properties.getProperty("dbURL"), properties.getProperty("dbUser"), properties.getProperty("dbPassword"));
        } catch (SQLException | IOException e) {
            logger.error(() -> e.toString());
        }

        return null;
    }

    public void setPooledConnections(int numberOfConnections) {
        connections = new LinkedList<>();

        for (int i = 0; i < numberOfConnections; i++) {
            connections.add(getNewConnection());
        }
    }

    public Connection getConnection() {
        if (connections.size() == 0) {
            if (autoExpand) {
                connections.add(getNewConnection());
            } else {
                throw new NullPointerException();
            }
        }

        Connection connection = connections.remove();

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error(() -> e.toString());
        }

        return connection;
    }

    public void closeConnections() {
        try {
            for (int i = 0; i < connections.size(); i++) {
                connections.remove().close();
            }
        } catch (SQLException e) {
            logger.error(() -> e.toString());
        }
    }

    public void returnConnection(Connection connection) {
        connections.add(connection);
    }
}
