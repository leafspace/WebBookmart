package cn.cslg.WebBookmart.servlet;

import cn.cslg.WebBookmart.model.User;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpVip extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("userInfo");
        user.level = 30;
        user.updataSql += "update user SET level=level+30 where userName='"+ user.userName + "';";
        request.setAttribute("userInfo",user);
        request.getRequestDispatcher("UserPage.jsp").forward(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
