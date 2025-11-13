package com.korit.servlet_study.ch11.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 요청 데이터 인코딩
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // 응답 데이터 인코딩
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        // 응답 데이터 타입
        response.setContentType("application/json");
        // 다음 필터 또는 서블릿으로 체이닝
        chain.doFilter(request, response);
    }
}
