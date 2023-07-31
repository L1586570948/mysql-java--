package com.Supermar.domain;

import java.time.LocalDateTime;

/**
 * @Author：林杰
 * @Package：com.Supermar.domain
 * @Project：IdeaProjects
 * @name：Collection
 * @Date：2023/7/30 18:50
 * @Filename：Collection
 */
public class Collection {

    private String Uid;
    private String Type;
    private String Cname;
    private Double Cprice;
    private LocalDateTime Caddtime;

    public Collection() {
    }

    public Collection(String uid, String type, String cname, Double cprice, LocalDateTime caddtime) {
        Uid = uid;
        Type = type;
        Cname = cname;
        Cprice = cprice;
        Caddtime = caddtime;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public Double getCprice() {
        return Cprice;
    }

    public void setCprice(Double cprice) {
        Cprice = cprice;
    }

    public LocalDateTime getCaddtime() {
        return Caddtime;
    }

    public void setCaddtime(LocalDateTime caddtime) {
        Caddtime = caddtime;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "Uid='" + Uid + '\'' +
                ", Type='" + Type + '\'' +
                ", Cname='" + Cname + '\'' +
                ", Cprice=" + Cprice +
                ", Caddtime=" + Caddtime +
                '}';
    }

}

