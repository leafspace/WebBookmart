package cn.cslg.WebBookmart.servlet;

import cn.cslg.WebBookmart.model.ShowingBean;
import cn.cslg.WebBookmart.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeShowing extends HttpServlet {
    private User user = null;
    private ShowingBean showingInfo = null;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if(request.getParameter("classificationName") == null | request.getParameter("index") == null
                | request.getParameter("isRemove") == null){
            System.out.println(request.getParameter("classificationName"));
            System.out.println(request.getParameter("index"));
            System.out.println(request.getParameter("isRemove"));
            System.out.println("Error : Servlet change showing has some problem");
        }else{
            String classificationName = request.getParameter("classificationName");
            int index = Integer.parseInt(request.getParameter("index"));
            boolean isRemove = Integer.parseInt(request.getParameter("isRemove")) == 1;
            user = (User) request.getSession().getAttribute("userInfo");
            showingInfo = (ShowingBean)request.getSession().getAttribute("showingInfo");

            int classificationIndex = -1;
            if(isRemove){
                classificationIndex = user.recycleBin.searchClassification(classificationName);
                showingInfo.setShowing(user.recycleBin.classifications.get(classificationIndex),index,isRemove);
            }else{
                classificationIndex = user.favorite.searchClassification(classificationName);
                showingInfo.setShowing(user.favorite.classifications.get(classificationIndex),index,isRemove);
            }

            request.getSession().setAttribute("showingInfo", showingInfo);
        }

        request.getRequestDispatcher("UserPage.jsp").forward(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
