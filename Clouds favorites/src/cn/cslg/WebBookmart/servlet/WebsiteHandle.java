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

/*完成增删改功能*/
/*    12 3*/
public class WebsiteHandle extends HttpServlet {
    private User user = null;
    private ShowingBean showingInfo = null;

    private HttpServletRequest request = null;
    private HttpServletResponse response = null;

    private boolean addBookmart(String classificationName, boolean isRemove){
        if(request.getParameter("websiteName") == null | request.getParameter("websiteName").equals("") |
                request.getParameter("website") == null | request.getParameter("website").equals("")){
            System.out.println("no this");
            return false;
        }
        String websiteName = this.request.getParameter("websiteName");
        String website = this.request.getParameter("website");

        if(website.indexOf("www.") < 0){
            website = "www." + website;
        }
        if(website.indexOf(".com") < 0){
            website = website + ".com";
        }

        Bookmart bookmart = new Bookmart(websiteName, website);
        if(bookmart.isRightful()){
            int tempIndex = user.favorite.searchClassification(classificationName);
            if (tempIndex < 0){
                user.favorite.classifications.add(new Classification(classificationName));
                tempIndex = user.favorite.searchClassification(classificationName);
                user.updataSql += "INSERT INTO classification VALUES('" + user.userName + "','" + classificationName + "');";
            }

            if(user.favorite.classifications.get(tempIndex).getSize() == user.level){
                try {
                    response.getWriter().println("<script type='javascript/text'>alert('您只能存放跟您等级相同个数的书签，添加新书签失败！');</script>");
                }catch (IOException e){
                    System.out.println("Error : The view of add bookmart");
                }
                return false;
            }else{
                try {
                    int i = 0;
                    for(; i < user.favorite.classifications.size(); ++i){
                        if(user.favorite.classifications.get(i).getName().equals(bookmart.getName())){
                            break;
                        }
                    }

                    if(i == user.favorite.classifications.size()) {
                        if(website.indexOf("http://") < 0){
                            bookmart.setWebsite("http://" + bookmart.getWebsite());
                        }
                        user.favorite.classifications.get(tempIndex).addBookmart(bookmart);
                        user.updataSql += "INSERT INTO bookmart VALUES('" + user.userName + "','" + classificationName + "', '" + bookmart.getName() + "','" + bookmart.getWebsite() + "',0);";
                        response.getWriter().println("<script type='javascript/text'>alert('添加成功');</script>");
                    }else{
                        response.getWriter().println("<script type='javascript/text'>alert('新书签与已有书签重名，添加新书签失败！');</script>");
                    }

                }catch (IOException e){
                    System.out.println("Error : The view of add bookmart");
                    return false;
                }
            }
        }else{
            try {
                response.getWriter().println("<script type='javascript/text'>alert('您输入的书签不合法或您与此网页ping不通，添加新书签失败！');</script>");
                return false;
            }catch (IOException e){
                System.out.println("Error : The view of add bookmart");
                return false;
            }
        }

        return true;
    }

    private boolean removeBookmart(String classificationName, String bookmartName, boolean isRemove){
        if(isRemove == false){
            int classificationIndex = user.favorite.searchClassification(classificationName);
            if (classificationIndex < 0){
                System.out.println("Error : This classification you not found , system error !");
                return false;
            }
            int bookmartIndex = -1;
            for(int i = 0; i < user.favorite.classifications.get(classificationIndex).getSize(); ++i){
                if(user.favorite.classifications.get(classificationIndex).getBookmart(i).getName().equals(bookmartName)){
                    bookmartIndex = i;
                    break;
                }
            }
            if(bookmartIndex != -1){
                Bookmart bookmart = user.favorite.classifications.get(classificationIndex).getBookmart(bookmartIndex);
                user.favorite.classifications.get(classificationIndex).removeBookmart(bookmartIndex);
                int recycleBinIndex = user.recycleBin.searchClassification(classificationName);
                if(recycleBinIndex < 0){
                    user.recycleBin.classifications.add(new Classification(classificationName));
                    recycleBinIndex = user.recycleBin.searchClassification(classificationName);
                }

                int bookmartIndexofRecycleBin = -1;
                for(int i = 0; i < user.recycleBin.classifications.get(recycleBinIndex).getSize(); ++i){
                    if(user.recycleBin.classifications.get(recycleBinIndex).getBookmart(i).getName().equals(bookmart.getName())){
                        bookmartIndexofRecycleBin = i;
                    }
                }
                if(bookmartIndexofRecycleBin == -1){
                    user.recycleBin.classifications.get(recycleBinIndex).addBookmart(bookmart);
                }else{
                    user.recycleBin.classifications.get(recycleBinIndex).setBookmart(bookmartIndexofRecycleBin,bookmart);
                }
                user.updataSql += "UPDATE bookmart SET isRemove=1 WHERE userName='" + user.userName + "' and classification='" + classificationName + "' and websiteName='" + bookmartName + "';";
            }else{
                System.out.println("Error : This bookmart you not found , system error !");
                return false;
            }
        }else{
            int classificationIndex = user.recycleBin.searchClassification(classificationName);
            if (classificationIndex < 0){
                System.out.println("Error : This classification you not found , system error !");
                return false;
            }
            int bookmartIndex = -1;
            for(int i = 0; i < user.recycleBin.classifications.get(classificationIndex).getSize(); ++i){
                if(user.recycleBin.classifications.get(classificationIndex).getBookmart(i).getName().equals(bookmartName)){
                    bookmartIndex = i;
                    break;
                }
            }
            if(bookmartIndex != -1){
                user.recycleBin.classifications.get(classificationIndex).removeBookmart(bookmartIndex);
                if(user.recycleBin.classifications.get(classificationIndex).getSize() == 0){
                    user.recycleBin.classifications.remove(classificationIndex);
                }
                user.updataSql += "DELETE FROM bookmart WHERE userName='" + user.userName + "' and classification='" + classificationName + "' and websiteName='" + bookmartName + "' and isRemove=1;";
            }else{
                System.out.println("Error : This bookmart you not found , system error !");
                return false;
            }
        }
        return true;
    }

    private boolean editBookmart(String classificationName, String bookmartName, boolean isRemove){
        if (isRemove == false){
            if(request.getParameter("websiteName") == null | request.getParameter("websiteName").equals("") |
                    request.getParameter("website") == null | request.getParameter("website").equals("")){
                System.out.println("Error : Have not this !");
                return false;
            }
            String websiteName = this.request.getParameter("websiteName");
            String website = this.request.getParameter("website");
            if(website.indexOf("www.") < 0){
                website = "www." + website;
            }
            if(website.indexOf(".com") < 0){
                website = website + ".com";
            }
            Bookmart bookmart = new Bookmart(websiteName, website);
            if (!bookmart.isRightful()){
                try {
                    System.out.println("Error : This bookmart is not right !");
                    this.response.getWriter().println("<script type='javascript/text'>alert('您输入的书签不合法或您与此网页ping不通，添加新书签失败！');</script>");
                }catch (IOException e){
                    System.out.println("Error : The view of add bookmart");
                }
                return false;
            }
            if(website.indexOf("http://") < 0){
                bookmart.setWebsite("http://" + bookmart.getWebsite());
            }

            int classificationIndex = user.favorite.searchClassification(classificationName);
            if (classificationIndex < 0){
                System.out.println("Error : Can ot found this classifcation system error");
                return false;
            }
            int bookmartIndex = -1;
            for(int i = 0; i < user.favorite.classifications.get(classificationIndex).getSize(); ++i){
                if(user.favorite.classifications.get(classificationIndex).getBookmart(i).getName().equals(bookmartName)){
                    bookmartIndex = i;
                    break;
                }
            }
            if (bookmartIndex < 0){
                System.out.println("Error : Can ot found this classifcation system error");
                return false;
            }

            for(int i = 0; i < user.favorite.classifications.get(classificationIndex).getSize(); ++i){
                if(user.favorite.classifications.get(classificationIndex).getBookmart(i).getName().equals(bookmart.getName())){
                    try {
                        System.out.println("Error : Have the same name bookmart !");
                        this.response.getWriter().println("<script type='javascript/text'>alert('您在此分类下已经存在一个同名书签，添加书签失败！');</script>");
                    }catch (IOException e){
                        System.out.println("Error : The view of add bookmart");
                    }
                    return false;
                }
            }

            user.favorite.classifications.get(classificationIndex).setBookmart(bookmartIndex, bookmart);
            user.updataSql += "update bookmart set website='" + bookmart.getWebsite() + "' where userName='" + user.userName + "' and classification = '" + classificationName + "' and websiteName='" + bookmartName + "' and isRemove=0;\n" +
                    "update bookmart set websiteName='" + bookmart.getName() + "' where userName='" + user.userName + "' and classification = '" + classificationName + "' and websiteName='" + bookmartName + "' and isRemove=0;";
        }else{
            int classificationIndex = user.recycleBin.searchClassification(classificationName);
            if (classificationIndex < 0){
                System.out.println("Error : Can ot found this classifcation system error");
                return false;
            }
            int bookmartIndex = -1;
            for(int i = 0; i < user.recycleBin.classifications.get(classificationIndex).getSize(); ++i){
                if(user.recycleBin.classifications.get(classificationIndex).getBookmart(i).getName().equals(bookmartName)){
                    bookmartIndex = i;
                    break;
                }
            }
            if (bookmartIndex < 0){
                System.out.println("Error : Can ot found this classifcation system error");
                return false;
            }

            Bookmart bookmart = user.recycleBin.classifications.get(classificationIndex).getBookmart(bookmartIndex);

            int classificationIndexofFavorite = user.favorite.searchClassification(classificationName);
            if (classificationIndexofFavorite < 0){
                System.out.println("Error : Can ot found this classifcation system error");
                return false;
            }
            for(int i = 0; i < user.favorite.classifications.get(classificationIndexofFavorite).getSize(); ++i){
                if(user.favorite.classifications.get(classificationIndexofFavorite).getBookmart(i).equals(bookmartName)){
                    try {
                        System.out.println("Error : Have the same name bookmart !");
                        this.response.getWriter().println("<script type='javascript/text'>alert('您在此分类下已经存在一个同名书签，无法恢复书签！');</script>");
                    }catch (IOException e){
                        System.out.println("Error : The view of add bookmart");
                    }
                    return false;
                }
            }

            user.favorite.classifications.get(classificationIndexofFavorite).addBookmart(bookmart);
            user.recycleBin.classifications.get(classificationIndex).removeBookmart(bookmartIndex);
            if(user.recycleBin.classifications.get(classificationIndex).getSize() == 0){
                user.recycleBin.classifications.remove(classificationIndex);
            }
            user.updataSql += "update bookmart set isRemove=0 where userName='" + user.userName + "' and classification = '" + classificationName + "' and websiteName='" + bookmartName + "';";
        }
        return true;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("flag") == null | request.getParameter("classificationName") == null |
                request.getParameter("index") == null | request.getParameter("isRemove") == null){
            System.out.println(request.getParameter("flag"));
            System.out.println(request.getParameter("classificationName"));
            System.out.println(request.getParameter("index"));
            System.out.println(request.getParameter("isRemove"));
            System.out.println("Error : Servlet change showing has some problem");
        }else{
            int flag = Integer.parseInt(request.getParameter("flag"));
            if(flag == 2 | flag == 3){
                if(request.getParameter("bookmartName") == null){
                    request.getRequestDispatcher("UserPage.jsp").forward(request, response);
                }
            }
            String classificationName = request.getParameter("classificationName");
            String bookmartName = request.getParameter("bookmartName");
            int index = Integer.parseInt(request.getParameter("index"));
            boolean isRemove = Integer.parseInt(request.getParameter("isRemove")) == 1;
            this.user = (User) request.getSession().getAttribute("userInfo");
            this.showingInfo = (ShowingBean)request.getSession().getAttribute("showingInfo");

            this.request = request;
            this.response = response;

            switch (flag)
            {
                case 1 : this.addBookmart(classificationName, isRemove); break;
                case 2 : this.removeBookmart(classificationName, bookmartName, isRemove); break;
                case 3 : this.editBookmart(classificationName, bookmartName, isRemove); break;
                default: System.out.println("Error : Flag over !"); break;
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
