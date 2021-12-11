package com.fedynets.filters.login;

import com.fedynets.entity.User;
import com.fedynets.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        boolean errorLogin = false;
        String login = request.getParameter("login");
        UserService userService = new UserService();
        List<User> userList = userService.findAllUser();
        for(User user : userList){
            if(user.getLogin().equals(login)){
                errorLogin = true;
                chain.doFilter(request, response);
            }
        }
        if(!errorLogin){
            request.setAttribute("errorPasswordOrLogin", "1");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
}
