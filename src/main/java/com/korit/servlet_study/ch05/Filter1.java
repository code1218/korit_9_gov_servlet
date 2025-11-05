package com.korit.servlet_study.ch05;

public class Filter1 implements Filter {
    @Override
    public void doFilter(Request req, Response resp, FilterChain filterChain) {
        filterChain.doFilter(req, resp);
        System.out.println("필터1 후처리");
    }
}
