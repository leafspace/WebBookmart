package cn.cslg.WebBookmart.model;

import cn.cslg.WebBookmart.database.MyDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserShowingBean {
    public String userName;
    public int size;
    public int level;
    public double experience;
    public int allSize;

    public UserShowingBean(User user){
        this.setUserShowingBean(user);
    }

    public void setUserShowingBean(User user){
        this.userName = user.userName;
        this.size = 0;

        MyDB myDB = new MyDB();
        MyDB.getPstmt("SELECT COUNT(*) FROM bookmart WHERE userName='" + this.userName + "' and isRemove=0;");
        ResultSet resultSet = MyDB.query();
        try {
            while (resultSet.next()) {
                this.size = Integer.parseInt(resultSet.getString("COUNT(*)"));
            }
        }catch (SQLException e){
            System.out.println("Error : MySql handle error !");
        }


        this.level = user.level;
        this.experience = user.experience;
        this.allSize = 0;


        MyDB.getPstmt("SELECT COUNT(*) FROM classification WHERE userName='" + this.userName + "';");
        resultSet = MyDB.query();
        try {
            while (resultSet.next()) {
                this.allSize = Integer.parseInt(resultSet.getString("COUNT(*)"));
            }
        }catch (SQLException e){
            System.out.println("Error : MySql handle error !");
        }


        this.allSize *= user.level;
        MyDB.allClose();
    }

}
