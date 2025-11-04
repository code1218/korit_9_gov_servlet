package com.korit.servlet_study.ch03;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@WebServlet("/ch03/users")
public class UserServlet extends HttpServlet {
    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = UserRepository.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userRepository.findAll();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User foundUser = userRepository.findByUsername();
        if (Objects.isNull(foundUser)) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(400)
                    .message("이미 존재하는 username입니다.")
                    .build();
            return;
        }
        userRepository.insert();
    }
}
