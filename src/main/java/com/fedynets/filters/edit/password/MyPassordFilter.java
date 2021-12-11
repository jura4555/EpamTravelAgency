package com.fedynets.filters.edit.password;

import com.fedynets.encryption.CryptPassword;
import com.fedynets.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "MyPassordFilter")
public class MyPassordFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String password = request.getParameter("my_password");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession httpSession = req.getSession(false);
        User user = (User) httpSession.getAttribute("User");
        if(!CryptPassword.check(password, user.getPassword())){
            request.setAttribute("errorMyPassword", "1");
            request.getRequestDispatcher("/jsp/edit_password.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }
}
