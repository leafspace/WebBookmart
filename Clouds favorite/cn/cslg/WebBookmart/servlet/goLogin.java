package cn.cslg.WebBookmart.servlet;

import cn.cslg.WebBookmart.database.MyDB;
import cn.cslg.WebBookmart.model.ShowingBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class goLogin extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("username") == null | request.getParameter("username").trim().equals("") |
                request.getParameter("password") == null | request.getParameter("password").trim().equals("")){
            request.setAttribute("errorInfo", "用户名或密码不可为空");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        MyDB myDB = new MyDB();
        MyDB.getPstmt("SELECT password FROM user WHERE username='" + username + "';");
        ResultSet resultSet = MyDB.query();

        String password_db = null;
        try {
            while (resultSet.next()){
                password_db = resultSet.getString("password");
                break;
            }
        } catch (SQLException e){
            System.out.println(e);
        }

        MyDB.allClose();

        if (password_db == null){
            request.setAttribute("errorInfo", "此用户不存在");
        }else if (!password.equals(password_db)){
            request.setAttribute("errorInfo", "密码错误");
        }else{
            myDB = new MyDB();
            MyDB.getPstmt("SELECT classification, bookmartName, website FROM bookmart WHERE username='" + username + "';");
            resultSet = MyDB.query();
            LinkedList<ShowingBean> showingBeans = new LinkedList<ShowingBean>();
            try {
                while (resultSet.next()){
                    String classification = resultSet.getString("classification");
                    String bookmartName = resultSet.getString("bookmartName");
                    String website = resultSet.getString("website");
                    showingBeans.add(new ShowingBean(username, classification, bookmartName, website));
                }
            } catch (SQLException e){
                System.out.println(e);
            }

            request.getSession().setAttribute("showingInfo", showingBeans);
            request.getSession().setAttribute("showingIndex", 1);
            request.getSession().setAttribute("username", username);
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
