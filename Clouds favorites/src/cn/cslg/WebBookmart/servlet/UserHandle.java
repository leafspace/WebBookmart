package cn.cslg.WebBookmart.servlet;

import cn.cslg.WebBookmart.model.Manager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserHandle extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("userName") == null | request.getParameter("userName").equals("") |
                request.getSession().getAttribute("managerInfo") == null | request.getSession().getAttribute("managerInfo").equals("")){
            System.out.println("Error : request data can not found !");
        }else{
            String userName = request.getParameter("userName");
            Manager manager = (Manager) request.getSession().getAttribute("managerInfo");

            for(int i = 0; i < manager.m_system.users.size(); ++i){
                if(manager.m_system.users.get(i).userName.equals(userName)){
                    manager.m_system.users.remove(i);
                    manager.updataSql += "DELETE FROM `user` WHERE userName = '" + userName + "' and isManager=0;";
                    manager.updataSql += "DELETE FROM classification WHERE username = '" + userName + "';";
                    manager.updataSql += "DELETE FROM bookmart WHERE username = '" + userName + "';";
                    break;
                }
            }

            request.getSession().setAttribute("managerInfo", manager);

        }

        request.getRequestDispatcher("ManagerPage.jsp").forward(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
