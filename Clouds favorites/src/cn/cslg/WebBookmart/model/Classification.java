package cn.cslg.WebBookmart.model;

import java.util.LinkedList;

public class Classification {
    private String classificationName;
    private LinkedList<Bookmart> bookmarts;

    public Classification(String classificationName){
        this.classificationName = classificationName;
        bookmarts = new LinkedList<Bookmart>();
    }

    public String getName(){
        return classificationName;
    }

    public Bookmart getBookmart(int index){
        if(index > bookmarts.size() | index < 0){
            return null;
        }else{
            return bookmarts.get(index);
        }
    }

    public int getSize(){
        return this.bookmarts.size();
    }

    public boolean setName(String classificationName){
        if(classificationName == null | classificationName.length() == 0){
            return false;
        }else if(classificationName.length() > 20){
            this.classificationName = classificationName.substring(19);                          //超过20个字符的部分去除
            System.out.println("Tip : Classification name just have 20 characters !");
        }else{
            this.classificationName = classificationName;
        }
        return true;
    }

    public boolean setBookmart(int index, Bookmart bookmart){                                    //必须保证bookmart的合法性
        if(index < 0 | index > this.bookmarts.size() | bookmart.isRightful()){
            return false;
        }else{
            this.bookmarts.set(index, bookmart);
            return true;
        }

    }

    public int searchBookmartByBookmartName(String bookmartName){
        for(int i = 0; i < this.bookmarts.size(); ++i){
            if(bookmartName.equals(this.bookmarts.get(i).getName())){
                return i;
            }
        }
        return -1;
    }

    public int searchBookmartByWebsite(String website){
        for(int i = 0; i < this.bookmarts.size(); ++i){
            if(website.equals(this.bookmarts.get(i).getWebsite())){
                return i;
            }
        }
        return -1;
    }

    public boolean addBookmart(Bookmart bookmart){
        if(this.searchBookmartByBookmartName(bookmart.getName()) > 0 |
                this.searchBookmartByWebsite(bookmart.getWebsite()) > 0){
            return false;
        }else{
            this.bookmarts.add(bookmart);
            return true;
        }
    }

    public boolean removeBookmart(int index){
        if(index < 0 | index > this.bookmarts.size()){
            System.out.println("Error : This bookmart space out the range");
            return false;
        }else{
            this.bookmarts.remove(index);
            return true;
        }
    }
}
