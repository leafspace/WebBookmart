package cn.cslg.WebBookmart.model;

import cn.cslg.WebBookmart.database.MyDB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public String userName;
    public String userPassword;
    public int level;
    public double experience;
    public Favorite favorite;
    public RecycleBin recycleBin;

    public String updataSql;

    public User(){
        updataSql = "";
        favorite = new Favorite();
        recycleBin = new RecycleBin();
    }

    public int getSize(int index){
        return favorite.classifications.get(index).getSize();
    }

    public int getAllSize(){
        int size = 0;
        for(int i = 0; i < favorite.classifications.size(); ++i){
            size += favorite.classifications.get(i).getSize();
        }
        return size;
    }

    private void initClassification(ResultSet resultSet, boolean isFavorite){
        try {
            while (resultSet.next()) {
                String classtemp = resultSet.getString("classification");
                if(isFavorite){
                    this.favorite.classifications.add(new Classification(classtemp));
                }else{
                    this.recycleBin.classifications.add(new Classification(classtemp));
                }
            }
        }catch (SQLException e){
            System.out.println("Error : MySql handle error !");
            System.out.println(e);
        }
    }

    private void initBookmart(MyDB m_db, boolean isFavorite){
        if(isFavorite){
            for(int i = 0; i < this.favorite.classifications.size(); ++i){
                String classificationName = this.favorite.classifications.get(i).getName();
                String tempSql = "select websiteName,website from bookmart where userName='" + this.userName +"' and classification='" + classificationName + "' and isRemove=0";
                m_db.getPstmt(tempSql);
                ResultSet resultSet = MyDB.query();
                try {
                    while (resultSet.next()) {
                        String websiteName = resultSet.getString("websiteName");
                        String website = resultSet.getString ("website");
                        Bookmart bookmart = new Bookmart(websiteName,website);
                        this.favorite.classifications.get(i).addBookmart(bookmart);
                    }
                }catch (SQLException e){
                    System.out.println("Error : MySql handle error !");
                    System.out.println(e);
                }
            }
        }else{
            for(int i = 0; i < this.recycleBin.classifications.size(); ++i){
                String classificationName = this.recycleBin.classifications.get(i).getName();
                String tempSql = "select websiteName,website from bookmart where userName='" + this.userName +"' and classification='" + classificationName + "' and isRemove=1";
                m_db.getPstmt(tempSql);
                ResultSet resultSet = MyDB.query();
                try {
                    while (resultSet.next()) {
                        String websiteName = resultSet.getString("websiteName");
                        String website = resultSet.getString ("website");
                        Bookmart bookmart = new Bookmart(websiteName,website);
                        this.recycleBin.classifications.get(i).addBookmart(bookmart);
                    }
                }catch (SQLException e){
                    System.out.println("Error : MySql handle error !");
                    System.out.println(e);
                }
            }
        }

    }

    public boolean initList(){
        String favoriteSql = "select classification from classification WHERE username='" + userName + "';";
        String recyclebinSql = "select DISTINCT classification from bookmart WHERE userName='" + userName + "' and isRemove = 1";
        MyDB m_db = new MyDB();

        /*获取各个分类*/
        MyDB.getPstmt(favoriteSql);
        ResultSet resultSet = MyDB.query();
        this.initClassification(resultSet,true);

        MyDB.getPstmt(recyclebinSql);
        resultSet = MyDB.query();
        this.initClassification(resultSet,false);

        /*获取各个书签*/
        initBookmart(m_db, true);
        initBookmart(m_db,false);
        MyDB.allClose();
        return true;
    }

    public boolean writeDatabase(){
        //此程序复杂，后写

        return true;
    }
}
