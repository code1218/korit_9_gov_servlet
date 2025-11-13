package com.korit.servlet_study.ch11.dto;

import com.korit.servlet_study.ch11.entity.Student;
import lombok.Data;
import lombok.Setter;

@Setter
public class StudentDto {
    private String name;
    private String phone;
    private String email;
    private int department;
    private int grade;
    private String major;
    private int admissionYear;

    public Student toEntity() {
        return Student.builder()
                .studentName(name)
                .phone(phone)
                .email(email)
                .departmentId(department)
                .grade(grade)
                .majorType(major)
                .admissionYear(Integer.toString(admissionYear))
                .build();
    }
}









