package com.fedynets.filters.edit.profile;

import com.fedynets.entity.User;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "NewEmailFilter")
public class NewEmailFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String email = request.getParameter("E-mail");
        boolean errorEmail = User.checkEmail(email);
        if(StringUtils.isBlank(email)) {
            chain.doFilter(request, response);
        }else{
            if(!errorEmail) {
                request.setAttribute("errorEmail", "1");
                request.getRequestDispatcher("/jsp/edit_profile.jsp").forward(request, response);
            }
            chain.doFilter(request, response);
        }
    }
}
