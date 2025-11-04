package com.korit.servlet_study.ch03;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse<T> {
    private int status = 200;
    private String message;
    private T body;
}
