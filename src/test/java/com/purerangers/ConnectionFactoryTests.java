package com.purerangers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.Connection;

public class ConnectionFactoryTests
{
    @Test
    @DisplayName("When the connection factory is asked to provide a connection it can do so.")
    public void canGetConnection()
    {
        ConnectionFactory cf = ConnectionFactory.getInstance();
        cf.setPooledConnections(1);

        Connection connection = cf.getConnection();

        Assertions.assertNotNull(connection);

        cf.returnConnection(connection);
    }

    @Test
    @DisplayName("When the factory is not auto expanding and is asked for a connection while empty it should throw a null pointer error")
    public void emptyConnectionThrowsNullException()
    {
        ConnectionFactory cf = ConnectionFactory.getInstance();
        cf.setAutoExpand(false);
        cf.setPooledConnections(1);

        Connection connectionOne = cf.getConnection();

        Assertions.assertThrows(NullPointerException.class, () ->
        {
            Connection connectionTwo = cf.getConnection();
        });
    }

    @Test
    @DisplayName("When the factory is auto expanding and is asked for a new connection while empty it should provide one")
    public void emptyConnectionAddsNewConnection()
    {
        ConnectionFactory cf = ConnectionFactory.getInstance();
        cf.setAutoExpand(true);
        cf.setPooledConnections(0);

        Connection connection = cf.getConnection();

        Assertions.assertNotNull(connection);
    }
}