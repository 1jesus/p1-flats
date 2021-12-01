package com.zhiyou.model.contract;

import com.zhiyou.model.house.House;
import com.zhiyou.model.lessee.Lessee;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;

/**
 * @Classname contract
 * @Date 2021/9/13 14:48
 */
public class Contract {
    private int cid;
    private String cnum;
    private int chid;
    private int clid;
    private Date ctime;
    private Date cstartTime;
    private Date cendTime;
    private String ctotalMoney;
    private int cpayType;

    //多表联查,携带另一个表的数据
    private House house;
    private Lessee lessee;

    @Override
    public String toString() {
        return "Contract{" +
                "cid=" + cid +
                ", cnum='" + cnum + '\'' +
                ", chid=" + chid +
                ", clid=" + clid +
                ", ctime=" + ctime +
                ", cstartTime=" + cstartTime +
                ", cendTime=" + cendTime +
                ", ctotalMoney='" + ctotalMoney + '\'' +
                ", cpayType=" + cpayType +
                ", house=" + house +
                ", lessee=" + lessee +
                '}';
    }

    public Lessee getLessee() {
        return lessee;
    }

    public void setLessee(Lessee lessee) {
        this.lessee = lessee;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCnum() {
        return cnum;
    }

    public void setCnum(String cnum) {
        this.cnum = cnum;
    }

    public int getChid() {
        return chid;
    }

    public void setChid(int chid) {
        this.chid = chid;
    }

    public int getClid() {
        return clid;
    }

    public void setClid(int clid) {
        this.clid = clid;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getCstartTime() {
        return cstartTime;
    }

    public void setCstartTime(Date cstartTime) {
        this.cstartTime = cstartTime;
    }

    public Date getCendTime() {
        return cendTime;
    }

    public void setCendTime(Date cendTime) {
        this.cendTime = cendTime;
    }

    public String getCtotalMoney() {
        return ctotalMoney;
    }

    public void setCtotalMoney(String ctotalMoney) {
        this.ctotalMoney = ctotalMoney;
    }

    public int getCpayType() {
        return cpayType;
    }

    public void setCpayType(int cpayType) {
        this.cpayType = cpayType;
    }
}
