<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 25. 11. 17.
  Time: 오후 4:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%  // 스크립틀릿
    String n = request.getParameter("num");
    int num = 0;
    if (n != null) {
        if (!n.isBlank()) {
            num = Integer.parseInt(n);
        }
    }
%>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <form action="/servlet_study_war_exploded/number">
            <input type="text" name="num">
            <button>추가</button>
            <ul>
                <%
                    for (int i = 0; i < num; i++) {
                %>
                        <li><%=i + 1%></li>
                <%
                    }
                %>

            </ul>
        </form>
    </body>
</html>
