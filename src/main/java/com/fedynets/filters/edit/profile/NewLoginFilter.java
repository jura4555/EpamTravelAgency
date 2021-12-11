package com.fedynets.filters.edit.profile;

import com.fedynets.entity.User;
import com.fedynets.service.UserService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "NewLoginFilter")
public class NewLoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //HttpServletRequest req = (HttpServletRequest) request;
        String login = request.getParameter("login");

        if (StringUtils.isBlank(login)) {
            chain.doFilter(request, response);
        } else {
            boolean errorLogin = User.checkLogin(login);
            if (!errorLogin) {
                request.setAttribute("errorLog", "1");
                request.getRequestDispatcher("/jsp/edit_profile.jsp").forward(request, response);
            }
            UserService userService = new UserService();
            List<User> userList = userService.findAllUser();
            for(User u : userList) {
                if (u.getLogin().equals(login)) {
                    request.setAttribute("errorLogUnique", "1");
                    request.getRequestDispatcher("/jsp/edit_profile.jsp").forward(request, response);
                }
            }
            chain.doFilter(request, response);
        }
    }
}
