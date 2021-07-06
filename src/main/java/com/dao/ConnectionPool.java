package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private static ConnectionPool connectionPool;

    private List<Connection> connectionList = new ArrayList<>();

    public static ConnectionPool getConnectionPool() throws ClassNotFoundException {
        if (connectionPool==null){
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }

    private ConnectionPool() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    public Connection getConnection() throws SQLException {
        if (connectionList.size()>0){
            Connection result = connectionList.get(0);
            connectionList.remove(0);
            return result;
        }else {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/java_ee_db", "postgres", "1");
        }
    }

    public void returnConnection(Connection connection) {
        connectionList.add(connection);
    }
}
