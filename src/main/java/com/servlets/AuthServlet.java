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

        User user = new User(login, pass);

        try {
            if (UserController.getController().isAuth(user)){
                HttpSession session = request.getSession();
                session.setAttribute("login", login);
                response.sendRedirect("/");
            }else{
                response.sendRedirect("/login?error=1");
            }
        } catch (SQLException e) {
            response.sendRedirect("/login?error=2");
        } catch (ClassNotFoundException e) {
            response.sendRedirect("/login?error=3");
        }

    }
}
