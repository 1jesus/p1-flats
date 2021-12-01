package com.zhiyou.model.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname User
 * @Date 2021/9/9 20:27
 */
public class User {
    private int uid;
    private String uname;
    private String password;
    private String urealname;
    private int uroid;
    private Date uaddTime;
    private int ustatus;

    //多表联查,携带关联表的数据
    private ArrayList<Role> roleList;

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                ", urealname='" + urealname + '\'' +
                ", uroid=" + uroid +
                ", uaddTime=" + uaddTime +
                ", ustatus=" + ustatus +
                ", roleList=" + roleList +
                '}';
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrealname() {
        return urealname;
    }

    public void setUrealname(String urealname) {
        this.urealname = urealname;
    }

    public int getUroid() {
        return uroid;
    }

    public void setUroid(int uroid) {
        this.uroid = uroid;
    }

    public Date getUaddTime() {
        return uaddTime;
    }

    public void setUaddTime(Date uaddTime) {
        this.uaddTime = uaddTime;
    }

    public int getUstatus() {
        return ustatus;
    }

    public void setUstatus(int ustatus) {
        this.ustatus = ustatus;
    }

    public ArrayList<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(ArrayList<Role> roleList) {
        this.roleList = roleList;
    }
}
