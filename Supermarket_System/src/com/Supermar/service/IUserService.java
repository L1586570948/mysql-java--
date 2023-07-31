package com.Supermar.service;

import com.Supermar.domain.User_Information;

import java.util.List;

/**
 * @Author：林杰
 * @Package：com.Supermar.service
 * @Project：IdeaProjects
 * @name：IUserService
 * @Date：2023/7/28 8:16
 * @Filename：IUserService
 */
public interface IUserService {
    //增
    void Add_User(User_Information user);
    //删
    void Del_User(User_Information user);
    //改
    void Upd_User(User_Information user);
    //查
    User_Information Che_User(User_Information user);
    //根据用户名查询
    User_Information name_User(String name);
    //查所有用户
    List<User_Information> Dao_All_Che_User();
    //查找余额
    User_Information Dao_checkbalance(User_Information user);
    //修改余额
    void Dao_Rechar_balance(User_Information user);
}
