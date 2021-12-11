package com.fedynets.filters.edit.profile;

import com.fedynets.entity.User;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "NewSurnameFilter")
public class NewSurnameFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String surname = request.getParameter("surname");
        if(StringUtils.isBlank(surname)) {
            chain.doFilter(request, response);
        } else {
            boolean errorSurname = User.checkNameAndSurname(surname);
            if (!errorSurname) {
                request.setAttribute("errorSurname", "1");
                request.getRequestDispatcher("/jsp/edit_profile.jsp").forward(request, response);
            }
            chain.doFilter(request, response);
        }
    }

}
