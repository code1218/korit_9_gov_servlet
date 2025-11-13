package com.korit.servlet_study.ch11.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Department {
    private int departmentId;
    private String departmentCode;
    private String departmentName;
}
