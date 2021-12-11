package com.fedynets.filters;

import com.fedynets.dao.TourDao;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(filterName = "EncodingFilter")
public class EncodingFilter implements Filter {
    static final Logger LOG = LogManager.getLogger(EncodingFilter.class);

    private String encoding = "UTF-8";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        req.setCharacterEncoding(encoding);
        chain.doFilter(req, resp);
    }
    @Override
    public void init(FilterConfig config) throws ServletException {
        String encodingParam = config.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }

    }
}
