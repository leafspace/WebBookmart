package cn.cslg.WebBookmart.servlet;

import cn.cslg.WebBookmart.database.MyDB;
import cn.cslg.WebBookmart.model.ShowingBean;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class add extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("book_mart") == null | request.getParameter("book_mart").trim().equals("") |
                request.getParameter("website") == null | request.getParameter("website").trim().equals("")){
            request.getRequestDispatcher("add.jsp").forward(request, response);
        }

        String bookmartName = request.getParameter("book_mart");
        String classification = request.getParameter("select");
        String website = request.getParameter("website");

        String username = (String) request.getSession().getAttribute("username");
        LinkedList<ShowingBean> showingBeans = (LinkedList<ShowingBean>) request.getSession().getAttribute("showingInfo");
        showingBeans.add(new ShowingBean(username, classification, bookmartName, website));

        MyDB myDB = new MyDB();
        MyDB.getPstmt("INSERT INTO bookmart VALUES('" + username + "','" + classification + "','" + bookmartName + "', '" + website + "');");
        MyDB.update();
        MyDB.allClose();

        request.getSession().setAttribute("showingInfo", showingBeans);
        request.getRequestDispatcher("userPage.jsp").forward(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
