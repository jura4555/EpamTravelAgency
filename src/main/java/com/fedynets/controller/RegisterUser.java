package com.fedynets.controller;

import com.fedynets.constants.UserRole;
import com.fedynets.encryption.CryptPassword;
import com.fedynets.entity.User;
import com.fedynets.service.UserService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "RegisterUser", value = "/jsp/registration")
public class RegisterUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User.Builder bUser = new User.Builder();
        bUser.bSetLogin(request.getParameter("login"));
        bUser.bSetPassword(CryptPassword.getSaltedHash(request.getParameter("password")));
        bUser.bSetName(request.getParameter("name"));
        bUser.bSetSurname(request.getParameter("surname"));
        bUser.bSetEmail(request.getParameter("E-mail"));
        bUser.bSetUserRole(UserRole.REGISTERED_USER);
        bUser.bSetActive(true);
        UserService userService = new UserService();
        userService.addUser(bUser.getResult());
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/jsp/home.jsp");
        requestDispatcher.forward(request, response);

    }
}
