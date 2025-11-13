package com.korit.servlet_study.ch11.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
    private int studentId;
    private String studentName;
    private String phone;
    private String email;
    private int departmentId;
    private int grade;
    private String majorType;
    private String admissionYear;
}
