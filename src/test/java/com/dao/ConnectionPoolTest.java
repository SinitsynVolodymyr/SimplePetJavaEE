package com.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionPoolTest {

    @Test
    void getConnectionPool() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        } catch (ClassNotFoundException e) {
            fail(e.getMessage());
        }

    }

    @Test
    void getConnection() throws ClassNotFoundException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        try {
            Connection connection = connectionPool.getConnection();
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void returnConnection() throws ClassNotFoundException, SQLException {
        ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
        Connection connection = connectionPool.getConnection();
        connectionPool.returnConnection(connection);
        Connection connection2 = connectionPool.getConnection();
        Connection connection3 = connectionPool.getConnection();

        assertEquals(connection,connection2);
        assertNotEquals(connection,connection3);

    }
}