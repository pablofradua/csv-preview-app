package com.exasol.csv;

import javax.servlet.*;
import java.io.IOException;

/**
 * Set default character encoding to UTF-8 for all requests and responses of Servlets.
 */
public class CharacterEncodingFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void init(final FilterConfig filterConfig) {
        // not applicable
    }

    @Override
    public void destroy() {
        // not applicable
    }
}
