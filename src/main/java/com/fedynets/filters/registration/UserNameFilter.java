package com.fedynets.filters.registration;

import com.fedynets.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "UserNameFilter")
public class UserNameFilter implements Filter {
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
        boolean errorName = User.checkNameAndSurname(name);
        if (!errorName) {
            request.setAttribute("errorName", "1");
            request.setAttribute("login", login);
            request.setAttribute("surname", surname);
            request.setAttribute("email", email);
            req.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }
    }
}
