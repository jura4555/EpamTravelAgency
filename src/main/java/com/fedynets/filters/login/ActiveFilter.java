package com.fedynets.filters.login;

import com.fedynets.entity.User;
import com.fedynets.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "ActiveFilter")
public class ActiveFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String login = request.getParameter("login");
        UserService userService = new UserService();
        List<User> userList = userService.findAllUser();
        User user = userService.findUserByLogin(login);
        if(user.isActive() == false){
            request.setAttribute("errorActive", "1");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }
}
