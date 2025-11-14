package com.korit.servlet_study.ch11.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Professor {
    private int professorId;
    private String professorName;
}
