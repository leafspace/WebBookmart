<%--
  Created by IntelliJ IDEA.
  User: 18852
  Date: 2016/11/26
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/login_Styles.css">
        <link rel="icon" type="image/x-ico" href="img/cloud-icon.png">
        <title>leafspace网络云收藏</title>
    </head>
    <body>
        <div class="htmleaf-container">
            <div class="wrapper">
                <div class="container">
                    <h1>登陆到我的云收藏</h1>

                    <form class="form" action="goLogin" method="post">
                        <input name="userName" type="text" placeholder="用户名">
                        <input name="password" type="password" placeholder="密码">
                        <button type="submit">登陆</button>

                    </form>
                    <form style="margin-top: -30px;" action="RegisterForm.jsp">
                        <button type="botton">注册</button>
                    </form>
                    <%
                        if(request.getAttribute("errorInfo") != null) {
                    %>
                            <div class="errorMsgBox">
                                <span id="info">${requestScope.errorInfo}</span>
                            </div>
                            <br>
                    <%
                        }
                    %>
                </div>
                <ul class="bg-bubbles">
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                    <li></li>
                </ul>
            </div>
        </div>
    </body>
</html>