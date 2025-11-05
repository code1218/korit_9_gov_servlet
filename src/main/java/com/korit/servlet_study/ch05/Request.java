package com.korit.servlet_study.ch05;

import lombok.Data;

@Data
public class Request {
    private String url;
    private String method;
    private String data;
    private String characterEncoding;
}
