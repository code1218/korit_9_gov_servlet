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

        List<Filter> filters = List.of(
                new Filter1(),
                new Filter2(),
                new Filter3()
        );

        FilterChain filterChain = new FilterChain(filters, servletMap.get(request.getUrl()), 0);
        filterChain.doFilter(request, response);

        System.out.println(response);
        System.out.println("응답");
    }
}
