package com.korit.servlet_study.ch09;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//@WebFilter("/study/students")
public class SecurityFilter implements Filter {
    private final String SECRET = "1234";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String secret = request.getHeader("Secret");
        System.out.println(secret);

        if (!SECRET.equals(secret)) {
            response.setStatus(401);
            objectMapper.writeValue(response.getWriter(), Map.of("message", "요청 권한이 없습니다."));
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
