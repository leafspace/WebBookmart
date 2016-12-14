<%--
  Created by IntelliJ IDEA.
  User: 18852
  Date: 2016/12/11
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>添加书签</title>
    </head>
    <body>
        <form action="add" method="post">
            书签名：<input type="text" name="book_mart" /> <br />
            分类名：<select name="select">
                        <option value="默认收藏夹" selected></option>
                        <option value="电影">电影</option>
                        <option value="视频">视频</option>
                        <option value="音乐">音乐</option>
                        <option value="资讯">资讯</option>
                    </select>
                    <br />
            书签地址：<input type="text" name="website" /> <br />
            <input type="submit" value="提交">
        </form>
    </body>
</html>
