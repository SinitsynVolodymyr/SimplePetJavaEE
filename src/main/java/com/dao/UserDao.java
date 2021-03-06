package com.dao;

import com.entity.User;
import com.exceptions.controller.UserIsExistException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User, String> {
    private static final String tableName = "accounts";
    private static UserDao controller;

    public static UserDao getController() throws SQLException, ClassNotFoundException {
        if (controller==null){
            controller = new UserDao();
        }
        return controller;
    }

    private UserDao() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public List<User> getAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        PreparedStatement ps = getPrepareStatement("SELECT * FROM "+this.getTableName());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User(rs.getString("name"));
                user.setMoney(rs.getFloat("money"));
                userList.add(user);
            }
            closePrepareStatement(ps);
            rs.close();

        return userList;
    }


    @Override
    public String getTableName() {
        return tableName;
    }

    public boolean isAuth(User user) throws SQLException {
        if (user.getPass()!=null){

            String sqlQuery = "SELECT name, pass FROM "+this.getTableName()+" WHERE lower(name) = '"+user.getLowerLogin()+"'";
            PreparedStatement ps = getPrepareStatement(sqlQuery);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()){
                boolean result = false;
                if(user.getPass().equals(resultSet.getString("pass")))
                    result =  true;

                closePrepareStatement(ps);
                resultSet.close();
                return result;

            }else{
                return false;
            }


        }else{
            return false;
        }
    }

    @Override
    public User getEntityByKey(String login) throws SQLException {
        login = login.toLowerCase();

        PreparedStatement ps = getPrepareStatement("SELECT * FROM " + this.getTableName() + " WHERE lower(name)='" + login + "'");
        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()){
            User user = new User(resultSet.getString("name"));
            user.setMoney(resultSet.getFloat("money"));
            closePrepareStatement(ps);
            resultSet.close();
            return user;
        }

        return null;
    }

    @Override
    public boolean update(User entity) throws SQLException {
        String login = entity.getLogin().toLowerCase();

        PreparedStatement prepareStatement;
        if (entity.getPass()!=null) {
            prepareStatement = getPrepareStatement("UPDATE "+this.getTableName()+" SET " +
                    "name = '" + entity.getLogin() + "', " +
                    "pass = '" + entity.getPass() + "', " +
                    "money = " + entity.getMoney() +
                    "  WHERE lower(name) = '" + login + "'");
        }else{
            prepareStatement = getPrepareStatement("UPDATE "+this.getTableName()+" SET " +
                    "name = '" + entity.getLogin() + "', " +
                    "money = " + entity.getMoney() +
                    "  WHERE lower(name) = '" + login + "'");
        }
        prepareStatement.execute();
        closePrepareStatement(prepareStatement);
        return true;
    }

    @Override
    public boolean remove(String login) throws SQLException {
        login = login.toLowerCase();
        String sqlQuery = "DELETE FROM "+this.getTableName()+" WHERE lower(name) = '"+login+"'";

        PreparedStatement prepareStatement = getPrepareStatement(sqlQuery);
        prepareStatement.execute();
        closePrepareStatement(prepareStatement);
        return true;
    }

    @Override
    public boolean create(User user) throws SQLException, UserIsExistException {

        if (getEntityByKey(user.getLogin())==null) {
            String sqlQuery = "INSERT INTO " + this.getTableName() + " (name, pass, money) " +
                    "VALUES ('" + user.getLogin() + "', '" + user.getPass() + "', " + user.getMoney() + ")";
            PreparedStatement ps = getPrepareStatement(sqlQuery);
        //    return ps.execute();

            ps.execute();
            closePrepareStatement(ps);
          return true;
        }else{
            throw new UserIsExistException();
        }

    }

    public boolean updateMoney(User user) throws SQLException {

        String sqlQuery = "UPDATE "+this.getTableName()+" SET " +
                "money = "+user.getMoney()+" " +
                "WHERE lower(name) = '"+user.getLowerLogin()+"'";

        PreparedStatement ps = getPrepareStatement(sqlQuery);
        ps.execute();
        closePrepareStatement(ps);
        return true;
    }

    public boolean sendMoney(User userFrom, User userTo) throws SQLException {

        Connection con = getConnection();

        String sqlQueryUserFrom = "UPDATE "+this.getTableName()+" SET " +
                "money = "+userFrom.getMoney()+" " +
                "WHERE lower(name) = '"+userFrom.getLowerLogin()+"'";

        String sqlQueryUserTo = "UPDATE "+this.getTableName()+" SET " +
                "money = "+userTo.getMoney()+" " +
                "WHERE lower(name) = '"+userTo.getLowerLogin()+"'";


        try (PreparedStatement updateUserFrom = con.prepareStatement(sqlQueryUserFrom);
             PreparedStatement updateUserTo = con.prepareStatement(sqlQueryUserTo))
        {
            con.setAutoCommit(false);
            updateUserFrom.execute();
            updateUserTo.execute();
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            return false;
        }

        return true;
    }


}
