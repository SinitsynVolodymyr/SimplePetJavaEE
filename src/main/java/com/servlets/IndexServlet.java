package com.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "IndexServlet", value = "/")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getSession().getAttribute("login") != null) {
                if (request.getSession().getAttribute("money") != null) {
                    RequestDispatcher rd = request.getRequestDispatcher("/content.jsp");
                    rd.forward(request, response);
                    request.getSession().removeAttribute("money");
                } else {
                    response.sendRedirect("/session");
                }
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/unAuth.jsp");
                rd.forward(request, response);
            }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
