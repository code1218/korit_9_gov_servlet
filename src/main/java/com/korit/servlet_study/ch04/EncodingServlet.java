package com.korit.servlet_study.ch04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/ch04/encoding")
public class EncodingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = req.getParameter("data");
        System.out.println(data);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.getWriter().println("한글");
        resp.getWriter().println("한글");
        resp.getWriter().println("한글");
        resp.getWriter().println("한글");
        resp.getWriter().println("한글");
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

    }
}
