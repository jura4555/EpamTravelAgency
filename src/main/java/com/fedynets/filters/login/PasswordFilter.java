package com.fedynets.filters.login;

import com.fedynets.encryption.CryptPassword;
import com.fedynets.entity.User;
import com.fedynets.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "PasswordFilter")
public class PasswordFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserService userService = new UserService();
        User user = userService.findUserByLogin(login);
        if(!CryptPassword.check(password, user.getPassword())){
            request.setAttribute("errorPasswordOrLogin", "1");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }
}
