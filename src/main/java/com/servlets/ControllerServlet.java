package com.servlets;

import com.dao.UserController;
import com.entity.User;
import com.exceptions.controller.UserIsExistException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

@WebServlet(name = "SessionServlet", value = "/session")
public class ControllerServlet extends HttpServlet {
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
        String req = request.getParameter("request");

        HttpSession session = request.getSession();

        if (req!=null){

            switch (req.toLowerCase()){
                case ("exit"):
                    request.getSession().invalidate();
                    response.sendRedirect("/");
                    break;
                case ("register"):
                    String regLogin = request.getParameter("reglogin");
                    String regPass = request.getParameter("regpass");
                    String regpassRepeat = request.getParameter("regpassRepeat");

                    if (regLogin!=null&&regPass!=null){
                        if (regLogin!=""&&regPass!="") {
                            if (regPass.equals(regpassRepeat)){
                                try {
                                    UserController controller = UserController.getController();
                                    User user = new User(regLogin, regPass);
                                    user.setMoney(1000);
                                    controller.create(user);

                                    request.getSession().invalidate();
                                    if (!AuthServlet.logIn(regLogin, regPass, request.getSession()))
                                        response.sendRedirect("/register?error=1&login="+regLogin);
                                    else
                                        response.sendRedirect("/");
                                } catch (SQLException throwables) {
                                    response.sendRedirect("/register?error=2&login="+regLogin);
                                } catch (ClassNotFoundException e) {
                                    response.sendRedirect("/register?error=3&login="+regLogin);
                                } catch (UserIsExistException e) {
                                    response.sendRedirect("/register?error=4&login="+regLogin);
                                }
                            }else{
                                response.sendRedirect("/register?error=5&login="+regLogin);
                            }
                        }
                    }



                    break;
                case ("delete"):
                    String login = (String) session.getAttribute("login");
                    if (login!=null){
                        try {
                            UserController controller = UserController.getController();
                            if (controller.remove(login))
                                session.invalidate();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    response.sendRedirect("/");
                    break;


                default:
                    response.sendRedirect("/");
                    break;

            }


        }
    }
}
