<%@ page import="java.util.LinkedList" %>
<%@ page import="cn.cslg.WebBookmart.model.ShowingBean" %><%--
  Created by IntelliJ IDEA.
  User: 18852
  Date: 2016/12/11
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    LinkedList<ShowingBean> showingBeans = null;
    int showingIndex = 1;
    if (request.getSession().getAttribute("showingInfo") == null | request.getSession().getAttribute("showingIndex") == null){
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }else{
        showingBeans = (LinkedList<ShowingBean>) request.getSession().getAttribute("showingInfo");
        String temp = request.getSession().getAttribute("showingIndex").toString();
        showingIndex = Integer.parseInt(temp);
    }
%>
<html>
    <head>
        <title>用户页面</title>
    </head>
    <body>
        <table>
            <tr>
                <td>号码</td>
                <td>书签名</td>
                <td>书签分类</td>
                <td>书签地址</td>
                <td>操作</td>
            </tr>
            <%
                for (int i = 0; i < showingBeans.size(); ++i){
                    if ((i / 10) == (showingIndex - 1)){
            %>
                        <tr>
                            <td><%=(i + 1)%></td>
                            <td><%=showingBeans.get(i).bookmartName%></td>
                            <td><%=showingBeans.get(i).classification%></td>
                            <td><%=showingBeans.get(i).website%></td>
                            <td><a href="<%=("remove?flag="+i)%>">删除</a></td>
                        </tr>
            <%
                    }
                }
            %>
        </table>
        <%
            for (int i = 1; i <= showingBeans.size() / 10 + 1; ++i){
        %>
                <span><a href="<%=("changeShow?flag="+i)%>"><%=i%></a></span>
        <%
            }
        %>
        <input type="button" value="添加" onclick="window.location.href='add.jsp'" />
    </body>
</html>
