package com.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class SessionServletTest extends Mockito {

    @Order(1)
    @Test
    public void testRegister() throws IOException, ServletException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("request")).thenReturn("Register");
        when(request.getParameter("reglogin")).thenReturn("Naruto");
        when(request.getParameter("regpass")).thenReturn("IWillBeHokage");
        when(request.getParameter("regpassRepeat")).thenReturn("IWillBeHokage");

        new SessionServlet().doPost(request, response);

        verify(response, times(1)).sendRedirect("/");
        verify(request, times(1)).getSession().invalidate();

    }


}