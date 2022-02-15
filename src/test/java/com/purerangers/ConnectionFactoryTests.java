package com.purerangers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionFactoryTests
{
    @Test
    @DisplayName("Throws exception with no available connection")
    public void throwsExceptionWithNoConnection()
    {
        ConnectionFactory cf = ConnectionFactory.getInstance();
        cf.setAutoExpand(false);
        cf.setPooledConnections(0);

        Assertions.assertThrows(NullPointerException.class, () ->
        {
            cf.getConnection();
        });
    }

    @Test
    @DisplayName("Expands with no available connection")
    public void expandsWithNoConnection()
    {
        ConnectionFactory cf = ConnectionFactory.getInstance();
        cf.setAutoExpand(true);
        cf.setPooledConnections(0);

        Connection connection = cf.getConnection();

        Assertions.assertNotNull(connection);
    }
}
