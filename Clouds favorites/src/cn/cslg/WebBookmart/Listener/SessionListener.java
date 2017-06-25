package cn.cslg.WebBookmart.Listener;

import cn.cslg.WebBookmart.database.MyDB;
import cn.cslg.WebBookmart.model.Manager;
import cn.cslg.WebBookmart.model.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;

public class SessionListener implements HttpSessionListener {
    private int getLevel(double experience){
        double[] level = new double[60];
        level[0] = 10;
        level[1] = 10;
        for(int i = 2; i < 60; ++i){
            level[i] = level[i - 1] + level[i - 2];
        }
        for(int i = 0; i < 60; ++i){
            if(level[i] > experience){
                return i + 1;
            }
        }
        return 60;
    }

    /* Session创建事件 */
    public void sessionCreated(HttpSessionEvent se) {
    }

    /* Session失效事件 */
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Session终于失效啦");

        ServletContext servletContext = se.getSession().getServletContext();
        User user = null;
        Manager manager = null;

        user = (User) se.getSession().getAttribute("userInfo");
        manager = (Manager) se.getSession().getAttribute("managerInfo");

        String[] updataSql = null;


        if(user != null){
            user.experience += 10;
            user.level = Math.max(user.level, this.getLevel(user.experience));
            user.updataSql += "update user SET level=" + user.level + " where userName='"+ user.userName + "';";
            user.updataSql += "update user SET experience=" + user.experience + " where userName='"+ user.userName + "';";

            updataSql = user.updataSql.split(";");
        }

        if(manager != null){
            updataSql = manager.updataSql.split(";");
        }

        MyDB myDB = new MyDB();
        for (int i = 0; i < updataSql.length; ++i){
            updataSql[i] += ";";
            System.out.println(updataSql[i]);

            MyDB.getPstmt(updataSql[i]);
            MyDB.update();
        }
        MyDB.allClose();

    }
}