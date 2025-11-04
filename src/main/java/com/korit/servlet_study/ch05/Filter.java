package com.korit.servlet_study.ch05;

public interface Filter {
    void doFilter(Request req, Response resp, FilterChain filterChain);
}
