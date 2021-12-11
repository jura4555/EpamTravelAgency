package com.fedynets.controller;

import com.fedynets.entity.User;
import com.fedynets.service.UserService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditUserProfile", value = "/jsp/edit_profile")
public class EditUserProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String name =  request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("E-mail");
        HttpSession httpSession = request.getSession(false);
        User user = (User) httpSession.getAttribute("User");
        User.Builder bUser = new User.Builder();

        bUser.bSetUserId(user.getUserId());
        if (StringUtils.isBlank(login)){
            bUser.bSetLogin(user.getLogin());
        } else{
            bUser.bSetLogin(login);
        }
        if (StringUtils.isBlank(name)){
            bUser.bSetName(user.getName());
        }else{
            bUser.bSetName(name);
        }
        if (StringUtils.isBlank(surname)) {
            bUser.bSetSurname(user.getSurname());
        }else{
            bUser.bSetSurname(surname);
        }
        if (StringUtils.isBlank(email)){
            bUser.bSetEmail(user.getEmail());
        } else{
            bUser.bSetEmail(email);
        }
        bUser.bSetPassword(user.getPassword());
        bUser.bSetUserRole(user.getUserRole());
        bUser.bSetActive(user.isActive());
        UserService userService = new UserService();
        userService.updateUser(bUser.getResult());
        httpSession.setAttribute("User", bUser.getResult());
        request.getRequestDispatcher("/jsp/account.jsp").forward(request, response);

    }
}
