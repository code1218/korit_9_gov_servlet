package com.korit.servlet_study.ch02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP 프로토콜 Method
 * 1. Get
 *      - 용도: 리소스 조회
 *      - 특징:
 *          서버로부터 데이터를 요청만 하고 수정하지 않음
 *          요청 데이터(파라미터)가 URL에 포함됨 ex: http://test.com/users?username=test1234
 *          브라우저 히스토리에 남음
 *          북마크 가능
 *          캐싱 가능
 *          멱등성 있음
 *
 * 2. Post
 *      - 용도: 새로운 리소스 생성
 *      - 특징:
 *          서버에 데이터를 전송하여 새로운 리소스 생성
 *          요청 데이터가 HTTP Body에 포함됨
 *          브라우저 히스토리에 남지 않음
 *          캐싱되지 않음
 *
 * 3. Put
 *      - 용도: 리소스 전체 수정/생성
 *      - 특징:
 *          리소스가 있으면 전체를 교체, 없으면 생성
 *          전체 데이터를 전송해야함
 *          멱등성 있음
 *
 * 4. Patch
 *      - 용도: 리소스 부분 수정
 *      - 특징:
 *          리소스의 일부만 수정
 *          Put보다 효율적(변경할 필드만 전송)
 *          멱등성 있음
 *
 * 5. Delete
 *      - 용도: 리소스 삭제
 *      - 특징:
 *          지정된 리소스를 삭제
 *          멱등성 있음
 *
 * 6. HEAD
 *      - 용도: 리소스 존재여부 또는 메타데이터 확인
 *
 * 7. OPTIONS
 *      - 용도: HTTP 메서드의 존재여부 또는 CORS 프리플라이트 요청에 사용
 *
 * 8. CONNECT
 *      - 용도: 프록시 서버를 통한 터널링에 사용, SSL 연결에 활용됨
 *
 * 9. TRACE
 *      - 용도: 디버깅, 요청 경로 루프백 테스트
 *
 */

@WebServlet("/ch02/method")
public class HttpMethodServlet extends HttpServlet {
    Map<String, String> datas = new HashMap<>(Map.of(
            "name", "김준일",
            "age", "32",
            "address", "동래구"
    ));

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET 요청 들어옴");

        // 요청
        System.out.println(req.getMethod());
        // 요청 데이터(파라미터)
        System.out.println(req.getParameter("datasKey"));
        String datasKey = req.getParameter("datasKey");

        System.out.println(datas.get(datasKey));
/// ////////////////////////////////////////////////////
        // 응답
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        PrintWriter out = resp.getWriter();
        out.println(datas.get(datasKey));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST 요청 들어옴");

        //요청
        System.out.println(req.getMethod());
        // 요청 데이터(파라미터)
        System.out.println(req.getParameter("textData"));
        datas.put(req.getParameter("keyName"), req.getParameter("value"));
/// ////////////////////////////////////////////////////
        resp.setStatus(201);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resp.getWriter().println("데이터 추가 성공!!");
    }
}
