package com.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractController<E, K> {
    private Connection connection;
    private ConnectionPool connectionPool;

    public AbstractController() throws ClassNotFoundException, SQLException {
        connectionPool = ConnectionPool.getConnectionPool();
        connection = connectionPool.getConnection();
    }

    public abstract boolean remove(K id);
    public abstract boolean create(E entity);
    public abstract List<E> getAll();
    public abstract E update(E entity);
    public abstract E getEntityByKey(K id);

    public PreparedStatement getPrepareStatement(String sql) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return preparedStatement;
    }

    public void closePrepareStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addConnection() {
        connectionPool.returnConnection(connection);
    }

}