package cn.cslg.WebBookmart.servlet;

import cn.cslg.WebBookmart.database.MyDB;
import cn.cslg.WebBookmart.model.ShowingBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;

public class remove extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("flag") == null){
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        }

        int flag = Integer.parseInt(request.getParameter("flag"));
        LinkedList<ShowingBean> showingBeans = (LinkedList<ShowingBean>) request.getSession().getAttribute("showingInfo");

        ShowingBean showingBean = showingBeans.get(flag);
        showingBeans.remove(flag);
        request.getSession().setAttribute("showingInfo", showingBeans);

        MyDB myDB = new MyDB();
        MyDB.getPstmt("DELETE FROM bookmart WHERE username='" + showingBean.username + "' and classification='" + showingBean.classification + "' and bookmartName='" + showingBean.bookmartName + "' and website='" + showingBean.website + "';");
        MyDB.update();
        MyDB.allClose();

        request.getRequestDispatcher("userPage.jsp").forward(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
