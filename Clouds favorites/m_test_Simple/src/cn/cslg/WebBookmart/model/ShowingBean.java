package cn.cslg.WebBookmart.model;

public class ShowingBean {
    public String username;
    public String classification;
    public String bookmartName;
    public String website;

    public ShowingBean(String username, String classification, String bookmartName, String website){
        this.username = username;
        this.classification = classification;
        this.bookmartName = bookmartName;
        this.website = website;
    }

}
