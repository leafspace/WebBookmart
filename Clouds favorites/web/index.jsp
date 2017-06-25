<%--
  Created by IntelliJ IDEA.
  User: 18852
  Date: 2016/11/29
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
    <a href="LoginForm.jsp">login</a>
    <%
      request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
    %>
  </body>
</html>
