package com.fedynets.filters.edit.password;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "RepeatNewPasswordFilter")
public class RepeatNewPasswordFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String newPassword = request.getParameter("new_password");
        String repeatNewPassword = request.getParameter("repeat_password");
        if(!newPassword.equals(repeatNewPassword)){
            request.setAttribute("errorRepeatNewPassword", "1");
            request.getRequestDispatcher("/jsp/edit_password.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }
}
