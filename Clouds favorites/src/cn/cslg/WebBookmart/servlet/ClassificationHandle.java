package cn.cslg.WebBookmart.servlet;

import cn.cslg.WebBookmart.model.Bookmart;
import cn.cslg.WebBookmart.model.Classification;
import cn.cslg.WebBookmart.model.ShowingBean;
import cn.cslg.WebBookmart.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClassificationHandle extends HttpServlet {

    private User user = null;
    private ShowingBean showingInfo = null;

    private HttpServletRequest request = null;
    private HttpServletResponse response = null;

    private boolean addClassification(){
        if (request.getParameter("classificationNameNew") == null | request.getParameter("classificationNameNew").equals("")){
            System.out.println("Error : Servlet get parameter have problem !");
            return false;
        }
        String classificationNameNew = request.getParameter("classificationNameNew");
        int classificationIndex = user.favorite.searchClassification(classificationNameNew);
        if(classificationIndex >= 0){
            try {
                System.out.println("Error : Have the same name classification !");
                this.response.getWriter().println("<script type='javascript/text'>alert('您已经有一个同名书签，无法新建收藏夹！');</script>");
            }catch (IOException e){
                System.out.println("Error : The view of add classification !");
            }
            return false;
        }
        user.favorite.classifications.add(new Classification(classificationNameNew));
        user.updataSql += "INSERT INTO classification VALUES('" + user.userName + "','" + classificationNameNew + "');";
        return true;
    }

    private boolean removeClassification(){
        if (request.getParameter("classificationName") == null | request.getParameter("classificationName").equals("") |
                request.getParameter("isRemove") == null | request.getParameter("isRemove").equals("")){
            System.out.println("Error : Servlet get parameter have problem !");
            return false;
        }
        String classificationName = request.getParameter("classificationName");
        boolean isRemove = Integer.parseInt(request.getParameter("isRemove")) == 1;
        if(isRemove == false){
            if(user.favorite.searchClassification(classificationName) < 0){
                return false;
            }
            int classificationIndex = user.favorite.searchClassification(classificationName);
            int classificationIndexofRecycleBin = user.recycleBin.searchClassification(classificationName);
            if(classificationIndexofRecycleBin < 0){
                user.recycleBin.classifications.add(new Classification(classificationName));
                classificationIndexofRecycleBin = user.recycleBin.searchClassification(classificationName);
            }
            for(int i = 0; i < user.favorite.classifications.get(classificationIndex).getSize(); ++i){
                Bookmart bookmart = user.favorite.classifications.get(classificationIndex).getBookmart(i);
                user.recycleBin.classifications.get(classificationIndexofRecycleBin).addBookmart(bookmart);
            }
            user.favorite.classifications.remove(classificationIndex);
            user.updataSql += "UPDATE bookmart SET isRemove=1 WHERE userName='" + user.userName + "' and classification='" + classificationName + "';";
        }else{
            if(user.recycleBin.searchClassification(classificationName) < 0){
                return false;
            }
            try {
                this.response.getWriter().println("<script type='javascript/text'>alert('您将删除此分类下所有的书签！');</script>");
            }catch (IOException e){
                System.out.println("Error : The view of add classification !");
            }
            int classificationIndex = user.recycleBin.searchClassification(classificationName);
            user.recycleBin.classifications.remove(classificationIndex);
            user.updataSql += "DELECT FROM bookmart where userName='" + user.userName + "' and classification='" + classificationName + "' and isRemove=1;";
            user.updataSql += "DELETE FROM classification WHERE username='" + user.userName + "' and classification='" + classificationName + "';";
        }
        return true;

    }

    private boolean editClassification(){
        if(request.getParameter("classificationName") == null | request.getParameter("classificationName").equals("") |
                request.getParameter("classificationNameNew") == null | request.getParameter("classificationNameNew").equals("")){
            System.out.println("Error : Servlet get parameter have problem !");
            return false;
        }
        String classificationName = request.getParameter("classificationName");
        String classificationNameNew = request.getParameter("classificationNameNew");
        if(user.favorite.searchClassification(classificationNameNew) >= 0){
            try {
                this.response.getWriter().println("<script type='javascript/text'>alert('您已经有此分类，重命名书签失败！');</script>");
            }catch (IOException e){
                System.out.println("Error : The view of add classification !");
            }
            return false;
        }
        int classificationIndex = user.favorite.searchClassification(classificationName);
        if(classificationIndex < 0){
            System.out.println("Error : System can not found this classification !");
            return false;
        }else{
            user.favorite.classifications.get(classificationIndex).setName(classificationNameNew);
            user.updataSql += "UPDATE bookmart SET classification='" + classificationNameNew +"' WHERE userName='" + user.userName + "' and classification='" + classificationName + "' and isRemove=0;";
            user.updataSql += "UPDATE classification SET classification='" + classificationNameNew + "' WHERE username='" + user.userName + "' and classification='" + classificationName + "';";
        }
        return true;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("flag") == null | request.getParameter("flag").equals("")){
            System.out.println("Error : Servlet get parameter have problem !");
        }else{
            int flag = Integer.parseInt(request.getParameter("flag"));
            this.user = (User) request.getSession().getAttribute("userInfo");
            this.showingInfo = (ShowingBean)request.getSession().getAttribute("showingInfo");

            this.request = request;
            this.response = response;

            switch (flag)
            {
                case 1: this.addClassification(); break;
                case 2: this.removeClassification(); break;
                case 3: this.editClassification(); break;
                default: System.out.println("Error have not this option !"); break;
            }

            request.getSession().setAttribute("userInfo", this.user);
            request.getSession().setAttribute("showingInfo", this.showingInfo);

        }

        request.getRequestDispatcher("ChangeShowing?classificationName=" + user.favorite.classifications.get(0).getName() + "&index=1&isRemove=0").forward(request, response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
}
