package com.fedynets.filters.registration;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "UserPassordFilter")
public class UserPassordFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("Repeat password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("E-mail");
        if(!password.equals(repeatPassword)){
            request.setAttribute("errorPassword", "1");
            request.setAttribute("login", login);
            request.setAttribute("name", name);
            request.setAttribute("surname", surname);
            request.setAttribute("email", email);
            req.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
        }else{
            chain.doFilter(request, response);
        }
    }
}
