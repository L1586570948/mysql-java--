package com.Supermar.domain;

import java.time.LocalDateTime;

/**
 * @Author：林杰
 * @Package：com.Supermar.domain
 * @Project：IdeaProjects
 * @name：History
 * @Date：2023/7/28 10:24
 * @Filename：History
 */
public class History {
    private String Uid;
    private String Cname;
    private LocalDateTime Browernow;

    public History() {
    }

    public History(String uid, String cname, LocalDateTime browernow) {
        Uid = uid;
        Cname = cname;
        Browernow = browernow;
    }

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

    public LocalDateTime getBrowernow() {
        return Browernow;
    }

    public void setBrowernow(LocalDateTime browernow) {
        Browernow = browernow;
    }
}
