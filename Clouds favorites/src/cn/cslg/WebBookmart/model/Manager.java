package cn.cslg.WebBookmart.model;


public class Manager extends User{
    public CloudsBookmartSystem m_system;

    public Manager(User user){
        this.level = user.level;
        this.experience = user.experience;
        this.userName = user.userName;
        this.userPassword = user.userPassword;
        m_system = new CloudsBookmartSystem();
    }
    public void Manager(CloudsBookmartSystem cb_System){
        this.m_system = cb_System;
    }
}
