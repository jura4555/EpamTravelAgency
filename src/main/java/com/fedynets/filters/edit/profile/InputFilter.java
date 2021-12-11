package com.fedynets.filters.edit.profile;

import freemarker.template.utility.StringUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "InputFilter")
public class InputFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String login = request.getParameter("login");
        String name =  request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("E-mail");
        if(StringUtils.isBlank(login) && StringUtils.isBlank(name) && StringUtils.isBlank(surname) && StringUtils.isBlank(email)){
            request.setAttribute("errorInput", "1");
            request.getRequestDispatcher("/jsp/edit_profile.jsp").forward(request, response);
        }
        chain.doFilter(request, response);
    }
}
