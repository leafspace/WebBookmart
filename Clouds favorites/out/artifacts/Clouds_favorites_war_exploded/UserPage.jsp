<%--
  Created by IntelliJ IDEA.
  User: 18852
  Date: 2016/11/28
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="cn.cslg.WebBookmart.model.User" %>
<%@ page import="cn.cslg.WebBookmart.model.User" %>
<%@ page import="cn.cslg.WebBookmart.model.ShowingBean" %>
<%
  User user = null;
  if(session.getAttribute("userInfo") != null){
    user = (User) session.getAttribute("userInfo");
  }else{
    request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
  }
  ShowingBean showingInfo = null;
  if(session.getAttribute("showingInfo") != null){
      showingInfo = (ShowingBean) session.getAttribute("showingInfo");
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
    <link rel="stylesheet" type="text/css" href="css/userStyles.css">
    <link rel="icon" type="image/x-ico" href="img/cloud-icon.png">
    <script src="js/inputData.js"></script>
    <title>我的云收藏</title>
  </head>
  <body>
  <script>var websiteName;var website;var classificationName;</script>
    <div id="main">
      <div id="top">
        <div id="t_text">网页云收藏夹</div>
        <div id="userOut" onclick="window.location.href='SignOut'">
          注销
        </div>

        <div id="vip" style="float: right;">
          <%
            if (user.level < 30){
          %>
              <a href="upVip">升级成VIP</a>
              level <%=user.level%>
          <%
            }else{
          %>
              vip <%=user.level%>
          <%
            }
          %>
        </div>
        <div id="userInfo">
          欢迎您 <%=user.userName%>
        </div>
      </div>
      <div id="center">
      </div>
      <div id="left">
        <div id="top-bar">
          <div id="top-bar-text">
            分类栏
          </div>
          <div id="top-bar-icon" onclick="disp2_prompt();window.location.href='ClassificationHandle?flag=1&classificationNameNew='+classificationName;stopevt();">
          </div>
        </div>

        <%
          for(int i = 0; i < user.favorite.classifications.size(); ++i){
              String tempUrl = "ChangeShowing?classificationName=" + user.favorite.classifications.get(i).getName() + "&index=1&isRemove=0";
              String tempUrlWebsiteHandle = "WebsiteHandle?flag=1&classificationName=" + user.favorite.classifications.get(i).getName() + "&index=1&isRemove=0";
              String tempUtlClassificationHandle = "ClassificationHandle?flag=3&classificationName=" + user.favorite.classifications.get(i).getName() + "&index=1&isRemove=0";
        %>
            <div class="kinds" onclick="window.location.href='<%=tempUrl%>'">
              <div class="kinds-text">
                <%=user.favorite.classifications.get(i).getName()%>
              </div>
              <div class="kinds-remove" onclick="disp_prompt();window.location.href='<%=tempUrlWebsiteHandle%>&websiteName=' + websiteName + '&website=' + website;stopevt();">
              </div>
              <div class="kinds-edit" onclick="disp2_prompt();window.location.href='<%=tempUtlClassificationHandle%>&classificationNameNew='+classificationName;stopevt();">
              </div>
            </div>
        <%
          }
        %>
        <div id="top-bar">
          <div id="top-bar-text">
            垃圾箱
          </div>
        </div>

        <%
          for(int i = 0; i < user.recycleBin.classifications.size(); ++i){
            String tempUrl = "ChangeShowing?classificationName=" + user.recycleBin.classifications.get(i).getName() + "&index=1&isRemove=1";
        %>
            <div class="kinds" onclick="window.location.href='<%=tempUrl%>'">
              <div class="kinds-text">
                <%=user.recycleBin.classifications.get(i).getName()%>
              </div>
            </div>
        <%
          }
        %>

      </div>

      <div id="show">
        <div id="show-top">
          <div id="show-top-text">
            <%
              if (!showingInfo.isRemove){
            %>
                我的收藏夹-<%=showingInfo.classificationName%>
            <%
              }
              else{
            %>
                我的垃圾箱-<%=showingInfo.classificationName%>
            <%
              }
            %>
          </div>
          <div id="show-top-remove" onclick="window.location.href='ClassificationHandle?flag=2&classificationName='+'<%=showingInfo.classificationName%>'+'&isRemove='+'<%=(showingInfo.isRemove ? "1":"0")%>';stopevt();">
            删除
          </div>
        </div>
        <hr>
        <div id="show-main">
          <%
            for(int i = 0; i < showingInfo.bookmarts.size(); ++i){
              String tempUrlWebsiteHandle_e = "WebsiteHandle?flag=3&classificationName=" + showingInfo.classificationName +
                      "&bookmartName=" + showingInfo.bookmarts.get(i).getName() + "&index=1&isRemove=" + (showingInfo.isRemove ? "1":"0");
              String tempUrlWebsiteHandle_r = "WebsiteHandle?flag=2&classificationName=" + showingInfo.classificationName +
                      "&bookmartName=" + showingInfo.bookmarts.get(i).getName() + "&index=1&isRemove=" + (showingInfo.isRemove ? "1":"0");
          %>
              <div id="<%=showingInfo.bookmarts.get(i).getName()%>" class="website">
                <div class="website-num"><%=(i + 1)%>.</div>
                <div class="website-name"><%=showingInfo.bookmarts.get(i).getName()%></div>
                <%
                  String website = showingInfo.bookmarts.get(i).getWebsite();
                %>
                <div class="website-address"><a href="<%=website%>" target="_blank"><%=website%></a></div>
                <%
                  if(!showingInfo.isRemove) {
                %>
                <div class="website-edit" onclick="disp_prompt();window.location.href='<%=tempUrlWebsiteHandle_e%>&websiteName=' + websiteName + '&website=' + website;stopevt();"></div>
                <%
                  }else {
                %>
                <div class="website-edit" style="background-image: url('img/websiteback.png');" onclick="window.location.href='<%=tempUrlWebsiteHandle_e%>';stopevt();"></div>
                <%
                  }
                %>
                <div class="website-remove" onclick="window.location.href='<%=tempUrlWebsiteHandle_r%>';stopevt();"></div>
              </div>
          <%
            }
          %>
          <%
            if(showingInfo.classificationSize > 1){
          %>
            <div id="showList" style="text-align: center; width: 100%;">
              &nbsp;&nbsp;
              <%
                for(int i = 1; i <= showingInfo.classificationSize; ++i){
                  String tempUrl = "ChangeShowing?classificationName=" + showingInfo.classificationName + "&index=" + i + "&isRemove="+(showingInfo.isRemove ? "1":"0");
              %>
                  <a href="<%=tempUrl%>"><%=i%></a>&nbsp;&nbsp;
              <%
                }
              %>
            </div>
          <%
            }
          %>
        </div>
      </div>
    </div>
  </body>
</html>
