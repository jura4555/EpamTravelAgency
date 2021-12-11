package com.fedynets.filters.edit.profile;

import com.fedynets.entity.User;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "NewNameFilter")
public class NewNameFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String name = request.getParameter("name");
        if (StringUtils.isBlank(name)) {
            chain.doFilter(request, response);
        } else {
            boolean errorName = User.checkNameAndSurname(name);
            if (!errorName) {
                request.setAttribute("errorName", "1");
                request.getRequestDispatcher("/jsp/edit_profile.jsp").forward(request, response);
            }
            chain.doFilter(request, response);
        }
    }
}
