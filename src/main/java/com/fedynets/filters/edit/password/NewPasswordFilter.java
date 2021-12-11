package com.fedynets.filters.edit.password;

import com.fedynets.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "NewPasswordFilter")
public class NewPasswordFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String newPassword = request.getParameter("new_password");
        if(!User.checkPassword(newPassword)){
            request.setAttribute("errorNewPassword", "1");
            request.getRequestDispatcher("/jsp/edit_password.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }
}
