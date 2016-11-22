package com.ironyard.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by reevamerchant on 11/14/16.
 */
public class RestSecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //check for key based authentication
        boolean authorized = false;
        String key = req.getHeader("x-authorization-key");
        if(key != null){
            authorized = SecurityUtils.isValidKey(key);
        }

        if (authorized) {
            chain.doFilter(request, response);
        } else {
            // tell them no
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            resp.getWriter().println("<html><body><p>No.. No..</p></body></html>");
        }
    }

    @Override
    public void destroy() {

    }



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
