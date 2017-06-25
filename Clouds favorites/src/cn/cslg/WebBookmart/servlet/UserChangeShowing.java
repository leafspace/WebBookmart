package cn.cslg.WebBookmart.servlet;

import cn.cslg.WebBookmart.model.Manager;
import cn.cslg.WebBookmart.model.UserShowingBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserChangeShowing extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("userName") == null | request.getParameter("userName").equals("") |
                request.getSession().getAttribute("managerInfo") == null | request.getSession().getAttribute("managerInfo").equals("") |
                request.getSession().getAttribute("showingInfo") == null | request.getSession().getAttribute("showingInfo").equals("")){
            System.out.println("Error : request data can not found !");
        }else{
            String userName = request.getParameter("userName");
            Manager manager = (Manager) request.getSession().getAttribute("managerInfo");
            UserShowingBean showingInfo = (UserShowingBean) request.getSession().getAttribute("showingInfo");

            for(int i = 0; i < manager.m_system.users.size(); ++i){
                if(manager.m_system.users.get(i).userName.equals(userName)){
                    showingInfo.setUserShowingBean(manager.m_system.users.get(i));
                    break;
                }
            }

            request.getSession().setAttribute("managerInfo", manager);
            request.getSession().setAttribute("showingInfo", showingInfo);

        }
        request.getRequestDispatcher("ManagerPage.jsp").forward(request, response);
}
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
