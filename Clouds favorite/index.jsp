<%--
  Created by IntelliJ IDEA.
  User: 18852
  Date: 2016/12/11
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String errorInfo = "";
  if (request.getAttribute("errorInfo") != null ){
      errorInfo = (String) request.getAttribute("errorInfo");
  }
%>
<html>
  <head>
    <title>登陆</title>
  </head>
  <body>
    <form action="goLogin" method="post">
      姓名:<input type="text" name="username" />
      <br />
      密码:<input type="password" name="password" />
      <br />
      <span style="color: red"><%=errorInfo%></span>
      <br />
      <input type="submit" value="登陆" />
      &nbsp;&nbsp;&nbsp;
      <input type="button" value="注册" onclick="window.location.href='register.jsp'">
    </form>
  </body>
</html>
