package com.korit.servlet_study.ch10;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ch10/courses")
public class CourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchData = req.getParameter("searchData");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        final String URL = "jdbc:mysql://localhost:3309/course_registration_db";
        final String USERNAME = "root";
        final String PASSWORD = "1q2w3e4r";

        try {
            // 1. DB Connection
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // 2. SQL 작성
            String sql = """
                    select
                        ct.course_id,
                        ct.course_code,
                        ct.course_name,
                        pt.professor_id,
                        pt.professor_name,
                        ct.credit,
                        ct.enrollment_capacity,
                        ct.classroom
                    from
                        course_tb ct
                        join professor_tb pt on pt.professor_id = ct.professor_id
                    where
                        ct.course_name like concat('%', ?, '%')
                    """;
            // 3. SQL문 실행을 위한 PreparedStatement 생성
            PreparedStatement ps = connection.prepareStatement(sql);

            // 4. ' ? ' 와일드카드 위치에 값 맵핑(1 = 몇 번째 물음표인지)
            ps.setString(1, searchData);

            // 5. 결과를 ResultSet 객체로 가져오기
            ResultSet rs = ps.executeQuery();

            @Data
            @AllArgsConstructor
            class Professor {
                private int professorId;
                private String professorName;
            }

            @Data
            @AllArgsConstructor
            class Course {
                private int courseId;
                private String courseCode;
                private String courseName;
                private Professor professor;
                private int credit;
                private int enrollmentCapacity;
                private String classroom;
            }

            List<Course> courses = new ArrayList<>();

            while (rs.next()) {
//                Map<String, Object> resultMap = Map.of(
//                        "course_id", rs.getInt("course_id"),
//                        "course_code", rs.getString("course_code"),
//                        "course_name", rs.getString("course_name"),
//                        "professor_name", rs.getString("professor_name"),
//                        "credit", rs.getInt("credit"),
//                        "enrollment_capacity", rs.getInt("enrollment_capacity"),
//                        "classroom", rs.getString("classroom")
//                );
                Map<String, Object> resultMap = new LinkedHashMap<>();
                resultMap.put("course_id", rs.getInt("course_id"));
                resultMap.put("course_code", rs.getString("course_code"));
                resultMap.put("course_name", rs.getString("course_name"));
                resultMap.put("professor_name", rs.getString("professor_name"));
                resultMap.put("credit", rs.getInt("credit"));
                resultMap.put("enrollment_capacity", rs.getInt("enrollment_capacity"));
                resultMap.put("classroom", rs.getString("classroom"));
                System.out.println(resultMap);



                Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_code"),
                        rs.getString("course_name"),
                        new Professor(rs.getInt("professor_id"), rs.getString("professor_name")),
                        rs.getInt("credit"),
                        rs.getInt("enrollment_capacity"),
                        rs.getString("classroom")
                );
                System.out.println(course);
                courses.add(course);
            }
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(resp.getWriter(), courses);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
