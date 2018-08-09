package com.way.fact.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import javax.servlet.*;
import java.io.IOException;

/**
 * 配置过滤器
 * @author yrz
 */
@Configuration
public class WebFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(WebFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest , servletResponse);
    }

    @Override
    public void destroy() {

    }
}
