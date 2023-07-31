package com.Supermar.domain;

/**
 * 用户信息
 */
public class User_Information {
    private String Uid;
    private String Account;
    private String Password;
    private double Balance;

    public User_Information() {
    }

    public User_Information(String uid, String account, String password, double balance) {
        Uid = uid;
        Account = account;
        Password = password;
        Balance = balance;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    @Override
    public String toString() {
        return "User_Information{" +
                "Uid='" + Uid + '\'' +
                ", Account='" + Account + '\'' +
                ", Password='" + Password + '\'' +
                ", Balance=" + Balance +
                '}';
    }
}
