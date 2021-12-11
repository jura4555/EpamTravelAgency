package com.fedynets.controller;

import com.fedynets.encryption.CryptPassword;
import com.fedynets.entity.User;
import com.fedynets.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditUserPassword", value = "/jsp/edit_password")
public class EditUserPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession(false);
        User user = (User) httpSession.getAttribute("User");
        user.setPassword(CryptPassword.getSaltedHash(request.getParameter("new_password")));
        UserService userService = new UserService();
        userService.updateUser(user);
        httpSession.setAttribute("User", user);
        request.getRequestDispatcher("/jsp/account.jsp").forward(request, response);
    }
}
