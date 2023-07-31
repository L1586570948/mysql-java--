package com.Supermar.domain;

/**
 * @Author：林杰
 * @Package：com.Supermar.domain
 * @Project：IdeaProjects
 * @name：Shopping
 * @Date：2023/7/28 10:24
 * @Filename：Shopping
 */
public class Shopping {
    private String Uid;
    private String Cname ;
    private double Cprice;
    private int number;

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Shopping() {
    }

    public Shopping(String uid, String cname, double cprice, int number) {
        Uid = uid;
        Cname = cname;
        Cprice = cprice;
        this.number = number;
    }
}
