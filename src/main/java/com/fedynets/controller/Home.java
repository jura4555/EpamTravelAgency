package com.fedynets.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Home", value = "/home")
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession(false);
        if(httpSession != null) {
            while (httpSession.getAttributeNames().hasMoreElements()) {
                httpSession.removeAttribute(httpSession.getAttributeNames().nextElement());
            }
        }

        //RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/home.jsp");
        //requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
