package com.korit.servlet_study.ch05;

import lombok.Data;

@Data
public class Response {
    private int status;
    private String characterEncoding;
    private String contentType;
    private String data;
}
