<%--
  Created by IntelliJ IDEA.
  User: 18852
  Date: 2016/11/28
  Time: 10:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/userStyles.css">
    <link rel="icon" type="image/x-ico" href="img/cloud-icon.png">
    <title>我的云收藏</title>
  </head>
  <body>
    <div id="main">
      <div id="top">
        <div id="t_text">网页云收藏夹</div>
        <div id="userOut">
          注销
        </div>

        <div id="vip" style="float: right;">
          <a href="index.jsp">升级成VIP</a>
        </div>

        <!--
        <div id="vip" style="float: right;">
          vip1
        </div>
        -->
        <div id="userInfo">
          欢迎您 leafspace
        </div>
      </div>
      <div id="center">
      </div>
      <div id="left">
        <div id="top-bar">
          <div id="top-bar-text">
            分类栏
          </div>
          <div id="top-bar-icon">
          </div>
        </div>

        <div class="kinds">
          <div class="kinds-text">
            科技资料
          </div>
          <div class="kinds-remove">
          </div>
          <div class="kinds-edit">
          </div>
        </div>

        <div class="kinds">
          <div class="kinds-text">
            搜索引擎
          </div>
          <div class="kinds-remove">
          </div>
          <div class="kinds-edit">
          </div>
        </div>

      </div>
      <div id="show">
        <div id="show-top">
          <div id="show-top-text">
            我的收藏夹-搜索引擎
          </div>
          <div id="show-top-remove">
            删除
          </div>
        </div>
        <hr>
        <div id="show-main">
          <div class="website">
            <div class="website-num">1.</div>
            <div class="website-name">百度搜索引擎</div>
            <div class="website-address"><a href="">www.baidu.com</a></div>
            <div class="website-edit"></div>
            <div class="website-remove"></div>
          </div>

          <div class="website">
            <div class="website-num">2.</div>
            <div class="website-name">谷歌搜索引擎</div>
            <div class="website-address"><a href="">www.google.com</a></div>
            <div class="website-edit"></div>
            <div class="website-remove"></div>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
