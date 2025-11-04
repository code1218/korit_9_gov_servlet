package com.korit.servlet_study.ch05;

import java.util.List;
import java.util.Map;

public class Tomcat {
    public static void main(String[] args) {
        System.out.println("요청");
        Request request = new Request();
        Response response = new Response();

        request.setUrl("/servlet/a");
        request.setMethod("GET");
        request.setData("요청 테스트 데이터");

        Map<String, Servlet> servletMap = Map.of(
                "/servlet/a", new ServletA(),
                "/servlet/b", new ServletB()
        );

        switch (request.getMethod()) {
            case "GET":
                servletMap.get(request.getUrl()).doGet(request, response);
                break;
            case "POST":
                servletMap.get(request.getUrl()).doPost(request, response);
                break;
        }

        System.out.println(response);
        System.out.println("응답");


    }
}
