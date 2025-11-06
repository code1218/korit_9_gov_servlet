package com.korit.servlet_study.ch09;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@WebServlet("/study/students")
public class StudentServlet extends HttpServlet {
    private StudentRepository studentRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = new StudentRepository();
        config.getServletContext().setAttribute("sr", studentRepository);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        resp.setContentType("application/json");

        String searchNameValue = req.getParameter("searchName");
        objectMapper.writeValue(resp.getWriter(), studentRepository.findAllBySearchNameValue(searchNameValue));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        resp.setContentType("application/json");

        Student student = objectMapper.readValue(req.getReader(), Student.class);
        studentRepository.insert(student);

        objectMapper.writeValue(resp.getWriter(), Map.of("message", "학생 추가 완료"));
    }

}
