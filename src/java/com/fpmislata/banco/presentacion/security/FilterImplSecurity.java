package com.fpmislata.banco.presentacion.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterImplSecurity implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest servletRequet, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Soy mas pro que jaime, imaginate...");
        filterChain.doFilter(servletRequet, servletResponse);
        System.out.println("Sigo siendo el pro");
    }

    @Override
    public void destroy() {
        
    }
    
}
