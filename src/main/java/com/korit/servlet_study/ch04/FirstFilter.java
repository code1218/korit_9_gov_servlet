package com.korit.servlet_study.ch04;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/ch04/*")
public class FirstFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("필터(전처리)");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("필터(후처리)");
    }

}
