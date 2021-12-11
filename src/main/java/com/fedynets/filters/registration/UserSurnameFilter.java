package com.fedynets.filters.registration;

import com.fedynets.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "UserSurnameFilter")
public class UserSurnameFilter implements Filter {
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
        boolean errorSurname = User.checkNameAndSurname(surname);
        if (!errorSurname) {
            request.setAttribute("errorSurname", "1");
            request.setAttribute("login", login);
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            req.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
        } else {
            chain.doFilter(request, response);
        }

    }
}
