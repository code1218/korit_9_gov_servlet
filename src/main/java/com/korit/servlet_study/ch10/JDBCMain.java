package com.korit.servlet_study.ch10;

import java.sql.*;

/**
 *  JDBC JavaDataBaseConnection
 *
 */
public class JDBCMain {
    public static void main(String[] args) {
        //    http://ip:port  -> http 프로토콜
        //    jdbc:mysql://ip:port -> jdbc:mysql 프로토콜
        //    mysql의 port: 기본(3306), 우리가 설정(3309)

        //    프로토콜://IP주소:PORT번호/데이터베이스(스키마)이름

        final String URL = "jdbc:mysql://localhost:3309/student_db";
        final String USERNAME = "root";
        final String PASSWORD = "1q2w3e4r";

        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");  // JDBC 4버전 이상부터는 생략가능
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String sql = """
                    select * from student_tb where student_name = '김준일'
                    """;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int studentId = rs.getInt("student_id");
            String studentName = rs.getString("student_name");
            System.out.println("id: " + studentId);
            System.out.println("name: " + studentName);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("데이터 베이스 연결 실패했어요.");
        }
    }
}












