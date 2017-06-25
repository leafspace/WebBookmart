package cn.cslg.WebBookmart.servlet;

import cn.cslg.WebBookmart.database.MyDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class goRegister extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("username") == null | request.getParameter("username").trim().equals("") |
                request.getParameter("password") == null | request.getParameter("password").trim().equals("")){
            request.setAttribute("errorInfo", "用户名或密码不可为空");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        MyDB myDB = new MyDB();
        MyDB.getPstmt("select password from `user` WHERE username='" + username + "';");
        ResultSet resultSet = MyDB.query();

        boolean isSign = false;
        try {
            while (resultSet.next()){
                isSign = true;
                break;
            }
        } catch (SQLException e){
            System.out.println(e);
        }

        if (isSign){
            request.setAttribute("errorInfo", "此用户已注册");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }else{
            MyDB.getPstmt("INSERT INTO `user` VALUES('" + username + "','" + password + "');");
            MyDB.update();
        }

        MyDB.allClose();
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
