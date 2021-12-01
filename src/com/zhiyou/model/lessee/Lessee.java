package com.zhiyou.model.lessee;

import com.zhiyou.model.house.House;

import java.util.Date;

/**
 * @Classname Lessee
 * @Date 2021/9/14 15:56
 */
public class Lessee {
    private int lid;
    private String lname;
    private String ltel;
    private int lsex;
    private String lnp;
    private String lidCard;
    private Date ladTime;

    @Override
    public String toString() {
        return "Lessee{" +
                "lid=" + lid +
                ", lname='" + lname + '\'' +
                ", ltel='" + ltel + '\'' +
                ", lsex=" + lsex +
                ", lnp='" + lnp + '\'' +
                ", lidCard='" + lidCard + '\'' +
                ", ladTime=" + ladTime +
                '}';
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLtel() {
        return ltel;
    }

    public void setLtel(String ltel) {
        this.ltel = ltel;
    }

    public int getLsex() {
        return lsex;
    }

    public void setLsex(int lsex) {
        this.lsex = lsex;
    }

    public String getLnp() {
        return lnp;
    }

    public void setLnp(String lnp) {
        this.lnp = lnp;
    }

    public String getLidCard() {
        return lidCard;
    }

    public void setLidCard(String lidCard) {
        this.lidCard = lidCard;
    }

    public Date getLadTime() {
        return ladTime;
    }

    public void setLadTime(Date ladTime) {
        this.ladTime = ladTime;
    }
}
