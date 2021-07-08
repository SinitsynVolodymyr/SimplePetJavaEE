package com.servlets;

import com.dao.UserController;
import com.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AuthServlet", value = "/auth")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        try {
            if (logIn(login,pass,request.getSession())){
                response.sendRedirect("/");
            }else {
                response.sendRedirect("/login?error=1&login="+login);
            }
        } catch (SQLException e) {
            response.sendRedirect("/login?error=2&login="+login);
        } catch (ClassNotFoundException e) {
            response.sendRedirect("/login?error=3&login="+login);
        }

    }

    public static boolean logIn(String login, String pass, HttpSession session) throws SQLException, ClassNotFoundException {
        User user = new User(login, pass);
        if (UserController.getController().isAuth(user)){
            session.setAttribute("login", login);
            return true;
        }else{
            return false;
        }
    }
}
