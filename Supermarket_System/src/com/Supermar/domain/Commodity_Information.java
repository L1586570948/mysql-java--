package com.Supermar.domain;

import java.time.LocalDateTime;

/**
 *商品信息
 */
public class Commodity_Information {
    private String Cid;
    private String Cname;
    private double Cprice;
    private String Type;
    //inventory是库存的意思
    private int Cinventory;
    //上架时间
    private LocalDateTime Caddtime;

    //非数据库字段
    private  int CNO;

    public int getCNO() {
        return CNO;
    }

    public void setCNO(int CNO) {
        this.CNO = CNO;
    }

    public Commodity_Information() {
    }

    public Commodity_Information(String cid, String cname, double cprice, String type, int cinventory, LocalDateTime caddtime) {
        Cid = cid;
        Cname = cname;
        Cprice = cprice;
        Type = type;
        Cinventory = cinventory;
        Caddtime = caddtime;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCid() {
        return Cid;
    }

    public void setCid(String cid) {
        Cid = cid;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public double getCprice() {
        return Cprice;
    }

    public void setCprice(double cprice) {
        Cprice = cprice;
    }

    public int getCinventory() {
        return Cinventory;
    }

    public void setCinventory(int cinventory) {
        Cinventory = cinventory;
    }

    public LocalDateTime getCaddtime() {
        return Caddtime;
    }

    public void setCaddtime(LocalDateTime caddtime) {
        Caddtime = caddtime;
    }

    @Override
    public String toString() {
        return "Commodity_Information{" +
                "Cid='" + Cid + '\'' +
                ", Cname='" + Cname + '\'' +
                ", Cprice=" + Cprice +
                ", Cinventory=" + Cinventory +
                ", Caddtime=" + Caddtime +
                '}';
    }
}
