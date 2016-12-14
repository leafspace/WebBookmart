package cn.cslg.WebBookmart.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class changeShow extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("showingIndex") == null){
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        }

        int showingIndex = Integer.parseInt(request.getParameter("flag"));

        request.getSession().setAttribute("showingIndex", showingIndex);
        request.getRequestDispatcher("userPage.jsp").forward(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
