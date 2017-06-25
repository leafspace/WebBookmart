package cn.cslg.WebBookmart.model;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Bookmart {
    private String bookmartName;
    private String website;

    public Bookmart(String bookmartName, String website){
        this.bookmartName = bookmartName;
        this.website = website;
    }

    public String getName(){
        return this.bookmartName;
    }

    public String getWebsite(){
        return this.website;
    }

    public boolean setName(String bookmartName){
        if (bookmartName == null | bookmartName.length() == 0){
            return false;
        }else if(bookmartName.length() > 16){
            this.bookmartName = bookmartName.substring(15);                  //超过16个字符的部分去除
            System.out.println("Tip : Just have 16 characters !");
        }else{
            this.bookmartName = bookmartName;
        }
        return true;
    }

    public boolean setWebsite(String website){
        if(website == null | website.length() < 0 | website.length() > 30){
            System.out.println("Error : This website address too long !");
            return false;
        }else{
            this.website = website;
            return true;
        }
    }

    public boolean isRightful(){                                             //保证当前的书签的合法性，为种类当中的添加提供功能
        if(this.bookmartName == null | this.bookmartName.length() == 0){
            return false;
        }

        if(this.website == null | this.website.length() == 0 | this.website.length() > 30){
            return false;
        }

        /*特色部分：如何能ping通网址，则此为一个正确的网址*/

        try {
            try {
                InetAddress address;
                address = InetAddress.getByName(this.website);
                if (address.isReachable(3000)) {
                    System.out.println(address.toString());
                    System.out.println("Name:" + address.getHostName());
                    System.out.println("Addr:" + address.getHostAddress());
                    System.out.println("Reach:" + address.isReachable(3000)); //是否能通信 返回true或false
                } else {
                    System.out.println("Error : This website is unreachable !");
                    return false;
                }
            }catch (UnknownHostException e){
                System.out.println("un know host exception !");
                return false;
            }
        } catch (IOException e) {
            System.out.println("IO exception !");
            return false;
        }



        if(this.bookmartName.length() > 16){
            this.bookmartName = this.bookmartName.substring(15);
        }

        return true;
    }
}
