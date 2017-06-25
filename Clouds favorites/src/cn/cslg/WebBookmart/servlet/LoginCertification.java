package cn.cslg.WebBookmart.servlet;

import cn.cslg.WebBookmart.database.MyDB;
import cn.cslg.WebBookmart.model.Manager;
import cn.cslg.WebBookmart.model.ShowingBean;
import cn.cslg.WebBookmart.model.User;
import cn.cslg.WebBookmart.model.UserShowingBean;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCertification extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");                                                          //获取姓名参数
        String password = request.getParameter("password");                                                          //获取密码参数

        String sql = "select * from user where userName = '"+ userName + "';";                                          //创建sql语句
        MyDB m_db = new MyDB();
        MyDB.getPstmt(sql);
        ResultSet resultSet = MyDB.query();                                                                             //从数据库中搜索出结果

        User user = new User();
        int resultNum = 0;                                                                                              //保存结果的个数
        boolean isManager = false;                                                                                      //判断是否是管理员
        try {
            while (resultSet.next()) {                                                                                  //获取用户数据并保存
                int level_DB = Integer.parseInt(resultSet.getString("level"));
                double experience_DB = Integer.parseInt(resultSet.getString("experience"));
                String userName_DB = resultSet.getString("userName");
                String password_DB = resultSet.getString("password");
                boolean isManager_DB = Integer.parseInt(resultSet.getString("isManager")) == 1;

                user.level = level_DB;
                user.experience = experience_DB;
                user.userName = userName_DB;
                user.userPassword = password_DB;
                isManager = isManager_DB;

                ++resultNum;
            }

            if(resultNum > 1){                                                                                          //如果有多个同名用户，说明出错了
                throw new SQLException("Error : Have more data .");
            }
        }catch (SQLException e){
            System.out.println("Error : MySql handle error !");
            System.out.println(e);
        }
        MyDB.allClose();

        String errorInfo = null;                                                                                        //如果有错，生成错误信息
        if(resultNum == 0){
            errorInfo = "没有此用户";
        }else{
            if(user.userPassword.equals(password) == false){
                errorInfo = "密码错误 请重输";
            }
        }

        if(errorInfo != null){
            user = null;
            request.setAttribute("errorInfo",errorInfo);
            request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
        }else{
            user.initList();                                                                                            //从数据库中读取书签信息
            if(isManager){
                Manager manager = new Manager(user);
                UserShowingBean showingInfo = new UserShowingBean(manager.m_system.users.get(0));
                request.getSession().setAttribute("managerInfo",manager);
                request.getSession().setAttribute("showingInfo",showingInfo);
                request.getRequestDispatcher("ManagerPage.jsp").forward(request, response);
            }else{
                ShowingBean showingInfo = new ShowingBean(user.favorite.classifications.get(0),1,false);
                request.getSession().setAttribute("userInfo",user);
                request.getSession().setAttribute("showingInfo",showingInfo);
                request.getRequestDispatcher("UserPage.jsp").forward(request, response);
            }
        }

    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
