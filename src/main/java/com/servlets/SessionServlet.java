package com.servlets;

import com.dao.UserController;
import com.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "SessionServlet", value = "/session")
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("login");
        if (login!=null){
            try {
                UserController controller = UserController.getController();
                User user = controller.getEntityByKey(login);
                request.getSession().setAttribute("money",user.getMoney());
                response.sendRedirect("/");

            } catch (SQLException throwables) {
                response.getWriter().write("Error connect to bd");;
            } catch (ClassNotFoundException e) {
                response.getWriter().write("Error driver load");
            }
        }else{
            response.getWriter().write("Error request");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
