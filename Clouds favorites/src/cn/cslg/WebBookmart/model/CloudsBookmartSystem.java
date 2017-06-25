package cn.cslg.WebBookmart.model;

import cn.cslg.WebBookmart.database.MyDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class CloudsBookmartSystem {
    public LinkedList<User> users;

    public CloudsBookmartSystem(){
        users = new LinkedList<User>();

        //Todo 从数据库中读取数据
        MyDB m_db = new MyDB();

        /*获取各个分类*/
        MyDB.getPstmt("select level,experience,userName,password from user where isManager = 0;");
        ResultSet resultSet = MyDB.query();
        try {
            while (resultSet.next()) {
                User user = new User();
                int level_DB = Integer.parseInt(resultSet.getString("level"));
                double experience_DB = Integer.parseInt(resultSet.getString("experience"));
                String userName_DB = resultSet.getString("userName");
                String password_DB = resultSet.getString("password");
                user.level = level_DB;
                user.experience = experience_DB;
                user.userName = userName_DB;
                user.userPassword = password_DB;
                user.initList();

                users.add(user);
            }
        }catch (SQLException e){
            System.out.println("Error : MySql handle error !");
            System.out.println(e);
        }
    }
}
