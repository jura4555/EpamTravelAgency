package com.fedynets.controller;

import com.fedynets.entity.User;
import com.fedynets.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/jsp/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        UserService userService = new UserService();
        User user = userService.findUserByLogin(login);
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("User", user);
        //RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/tour.jsp");
        //requestDispatcher.forward(request, response);
        response.sendRedirect("/jsp/tour.jsp");

    }
}
