package com.korit.servlet_study.ch05;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FilterChain {
    private List<Filter> filters;
    private Servlet servlet;
    private int currentOrder;

    public void doFilter(Request req, Response resp) {
        if (currentOrder < filters.size()) {
            filters.get(currentOrder++).doFilter(req, resp, this);
            return;
        }

        if ("GET".equalsIgnoreCase(req.getMethod())) {
            servlet.doGet(req, resp);
        } else if ("POST".equalsIgnoreCase(req.getMethod())) {
            servlet.doPost(req, resp);
        }
    }
}
