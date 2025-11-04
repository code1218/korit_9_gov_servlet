package com.korit.servlet_study.ch02;

import lombok.AllArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/ch02/users")
public class UserServlet extends HttpServlet {

    private List<User> users;

    @Override
    public void init() throws ServletException {
        users = new ArrayList<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // username == "test"
        // 찾으면 User객체 응답(200), 못찾으면 해당 username은 존재하지 않습니다.(404)
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        List<User> foundUsers = users.stream()
                .filter(user -> user.getUsername().equals(req.getParameter("username")))
                .toList();

        User foundUser = foundUsers.isEmpty() ? null : foundUsers.get(0);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (Objects.isNull(foundUser)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().println("해당 username은 존재하지 않습니다.");
            return;
        }
        resp.getWriter().println(foundUser);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);

//        User user = new User(username, password, name, email);

//        User user = User.builder()
//                .username(username)
//                .password(password)
//                .name(name)
//                .email(email)
//                .build();

        Map<String, String> error = validUser(user);

        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        if (!error.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println(error);
            return;
        }

        users.add(user);
        System.out.println(users);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("사용자 등록 완료");
    }

    private Map<String, String> validUser(User user) {
        Map<String, String> error = new HashMap<>();

        Arrays.stream(user.getClass().getDeclaredFields()).forEach(f -> {
            f.setAccessible(true);
            String fieldName = f.getName();
            System.out.println(fieldName);
            try {
                Object fieldValue = f.get(user);
                System.out.println(fieldValue);
                if (Objects.isNull(fieldValue)) {
                    throw new RuntimeException();
                }
                if (Objects.toString(fieldValue).isBlank()) {
                    throw new RuntimeException();
                }
            } catch (IllegalAccessException e) {
                System.out.println("필드에 접근할 수 없습니다.");
            } catch (RuntimeException e) {
                error.put(fieldName, "빈 값일 수 없습니다.");
            }
        });

        return error;
    }

}
