package com.korit.servlet_study.ch05;

public class Filter2 implements Filter {
    @Override
    public void doFilter(Request req, Response resp, FilterChain filterChain) {
        System.out.println("필터2 전처리");
        filterChain.doFilter(req, resp);
        System.out.println("필터2 후처리");
    }
}