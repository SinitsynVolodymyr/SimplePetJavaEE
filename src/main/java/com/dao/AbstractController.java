package com.dao;


import com.exceptions.controller.UserIsExistException;

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

    public abstract boolean remove(K id) throws SQLException;
    public abstract boolean create(E entity) throws SQLException, UserIsExistException;
    public abstract List<E> getAll() throws SQLException;
    public abstract boolean update(E entity) throws SQLException;
    public abstract E getEntityByKey(K id) throws SQLException;

    public abstract String getTableName();

    public PreparedStatement getPrepareStatement(String sql) throws SQLException {
        PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(sql);


        return preparedStatement;
    }

    public void closePrepareStatement(PreparedStatement ps) throws SQLException {
        if (ps != null) {
                ps.close();
        }
    }

    public void addConnectionToPull() {
        connectionPool.returnConnection(connection);
    }

}