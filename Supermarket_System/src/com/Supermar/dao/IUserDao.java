package com.Supermar.dao;

import com.Supermar.domain.User_Information;

import java.util.List;

/**
 * @Author：林杰
 * @Package：com.Supermar.dao
 * @Project：IdeaProjects
 * @name：IUserDao
 * @Date：2023/7/28 8:16
 * @Filename：IUserDao
 */
public interface IUserDao {
    //增
    void Dao_Add_User(User_Information user);
    //删
    void Dao_Del_User(User_Information user);
    //改
    void Dao_Upd_User(User_Information user);
    //查单一用户，uid查询
    User_Information Dao_Che_User(User_Information user);
    //根据用户名查询
    User_Information Dao_name_User(String name);
    //查所有用户
    List<User_Information> Dao_All_Che_User();
    //查询用户余额
    User_Information Dao_checkbalance  (User_Information user);

    void  Dao_RecharBalance (User_Information user);
}
