package com.fedynets.filters.registration;

import com.fedynets.entity.User;
import com.fedynets.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "UserLoginUniqueFilter")
public class UserLoginUniqueFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("E-mail");
        UserService userService = new UserService();
        List<User> userList = userService.findAllUser();
        for(User user : userList){
            if(user.getLogin().equals(login)){
                request.setAttribute("errorLoginUnique", "1");
                request.setAttribute("name", name);
                request.setAttribute("surname", surname);
                request.setAttribute("email", email);
                req.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
            }
        }
        chain.doFilter(request, response);
    }
}
