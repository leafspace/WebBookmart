package cn.cslg.WebBookmart.servlet;

import cn.cslg.WebBookmart.database.MyDB;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCertification extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean isManager = request.getParameter("radio").equals("是");
        String managerKey = request.getParameter("managerKey");
        String classification[] = request.getParameterValues("kinds");
        String userClassification = request.getParameter("user-define");
        String vipKey = request.getParameter("vip-key");

        boolean register = true;
        int level = vipKey.equals("123456") ? 30 : 1;

        if(username == null | username.equals("") | password == null | password.equals("")){
            register = false;
        }

        if(isManager & !managerKey.equals("123456")){
            isManager = false;
        }
        if(classification == null & (userClassification == null | userClassification.equals("")
                | userClassification.equals("如果有多个，请使用'、'分隔"))){
            classification = new String[1];
            classification[0] = "默认收藏夹";
        }

        if(register == false){
            request.getRequestDispatcher("RegisterForm.jsp").forward(request, response);
        }

        if(username.length() >= 16){
            username = username.trim();
            username = username.substring(0, 15);
        }
        if(password.length() >= 16){
            password = password.trim();
            password = password.substring(0, 15);
        }

        MyDB m_db = new MyDB();
        try{
            MyDB.getPstmt("select * from user where userName='"+ username +"';");
            ResultSet resultSet = MyDB.query();
            if(resultSet.next()){
                register = false;
            }
        }catch (SQLException e){
            System.out.println("Error : Database error !");
        }

        if(register){
            String tempSql = "INSERT into user VALUES(" + level +",0,'" + username+ "','" + password + "'," + (isManager?1:0) + ");";
            MyDB.getPstmt(tempSql);
            MyDB.update();

            if (classification != null) {
                for (int i = 0; i < classification.length; ++i) {
                    tempSql = "INSERT into classification VALUES('" + username + "','" + classification[i] + "');";
                    MyDB.getPstmt(tempSql);
                    MyDB.update();
                }
            }
            if (userClassification != null) {
                String[] tempClassification = userClassification.split("、");
                for (int i = 0; i < tempClassification.length; ++i) {
                    tempSql = "INSERT into classification VALUES('" + username + "','" + tempClassification[i] + "');";
                    MyDB.getPstmt(tempSql);
                    MyDB.update();
                }
            }

            MyDB.allClose();
            response.getWriter().println("<script type='javascript/text'>alert('注册成功');</script>");
            request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
        }else{
            MyDB.allClose();
            response.getWriter().println("<script type='javascript/text'>alert('用户名已存在');</script>");
            request.getRequestDispatcher("RegisterForm.jsp").forward(request, response);
        }
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
