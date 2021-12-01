package com.zhiyou.model.user;

import java.util.Date;

/**
 * @Classname Role
 * @Date 2021/9/16 20:23
 */
public class Role {
    private int roid;
    private String roname;
    private String rodesc;
    private Date roaddTime;

    @Override
    public String toString() {
        return "Role{" +
                "roid=" + roid +
                ", roname='" + roname + '\'' +
                ", rodesc='" + rodesc + '\'' +
                ", roaddTime=" + roaddTime +
                '}';
    }

    public int getRoid() {
        return roid;
    }

    public void setRoid(int roid) {
        this.roid = roid;
    }

    public String getRoname() {
        return roname;
    }

    public void setRoname(String roname) {
        this.roname = roname;
    }

    public String getRodesc() {
        return rodesc;
    }

    public void setRodesc(String rodesc) {
        this.rodesc = rodesc;
    }

    public Date getRoaddTime() {
        return roaddTime;
    }

    public void setRoaddTime(Date roaddTime) {
        this.roaddTime = roaddTime;
    }
}
