<%--
  Created by IntelliJ IDEA.
  User: 18852
  Date: 2016/11/29
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="cn.cslg.WebBookmart.model.UserShowingBean" %>
<%@ page import="cn.cslg.WebBookmart.model.Manager" %>
<%
  Manager manager = null;
  if(session.getAttribute("managerInfo") != null){
      manager = (Manager) session.getAttribute("managerInfo");
  }else{
    System.out.println("Error : Manager model has some problem.");
    request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
  }

  UserShowingBean showingInfo = null;
  if(session.getAttribute("showingInfo") != null){
    showingInfo = (UserShowingBean) session.getAttribute("showingInfo");
  }else{
    System.out.println("Error : Showing model has some problem.");
    request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
  }
%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="css/managerStyles.css">
  <script src="js/inputData.js"></script>
  <link rel="icon" type="image/x-ico" href="img/cloud-icon.png">
  <title>我的云收藏</title>
</head>
<body>
<div id="main">
  <div id="top">
    <div id="t_text">后台管理系统</div>
    <div id="userOut" onclick="window.location.href='SignOut'">
      注销
    </div>

    <div id="userInfo">
      欢迎您 <%=manager.userName%>
    </div>
  </div>
  <div id="center">
  </div>
  <div id="left">
    <div id="top-bar">
      <div id="top-bar-text">
        用户列表
      </div>
    </div>

    <%
      for(int i = 0; i < manager.m_system.users.size(); ++i){
    %>
        <div class="kinds" onclick="window.location.href='UserChangeShowing?userName='+'<%=manager.m_system.users.get(i).userName%>'; stopevt();">
          <div class="kinds-text">
            用户-<%=manager.m_system.users.get(i).userName%>
          </div>
          <div class="kinds-remove" onclick="window.location.href='UserHandle?userName='+'<%=manager.m_system.users.get(i).userName%>'; stopevt();">
          </div>
        </div>
    <%
      }
    %>
  </div>
  <div id="show">
    <div id="show-text">
      用户<%=showingInfo.userName%>拥有<%=showingInfo.size%>条记录
      <br />等级<%=showingInfo.level%>，经验<%=showingInfo.experience%>
      <br />可拥有<%=showingInfo.allSize%>个记录
    </div>
  </div>
</div>
</body>
</html>
