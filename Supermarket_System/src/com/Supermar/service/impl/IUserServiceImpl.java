package com.Supermar.service.impl;

import com.Supermar.dao.impl.IUserDaoImpl;
import com.Supermar.domain.User_Information;
import com.Supermar.service.IUserService;

import java.util.List;

/**
 * @Author：林杰
 * @Package：com.Supermar.service.impl
 * @Project：IdeaProjects
 * @name：IUserServiceImpl
 * @Date：2023/7/28 18:55
 * @Filename：IUserServiceImpl
 */
public class IUserServiceImpl implements IUserService {
    //用户类的业务处理层

    //创建一个dao包的接口对象
    private IUserDaoImpl iuserdaoimpl;
    //用构造器，获取对象
    public IUserServiceImpl() {
        //父类接口让子类实现,父类引用变量指向子类对象
        //解释：这样service包可以直接通过dao包的接口使用其子类实现的功能方法
        iuserdaoimpl = new IUserDaoImpl();
    }
    //增
    @Override
    public void Add_User(User_Information user) {
        iuserdaoimpl.Dao_Add_User(user);
    }
    //删
    @Override
    public void Del_User(User_Information user) {
        iuserdaoimpl.Dao_Del_User(user);
    }
    //改
    @Override
    public void Upd_User(User_Information user) {
        iuserdaoimpl.Dao_Upd_User(user);
    }
    //查一个人
    @Override
    public User_Information Che_User(User_Information user) {
        return iuserdaoimpl.Dao_Che_User(user);
    }

    @Override
    public User_Information name_User(String name) {
        return iuserdaoimpl.Dao_name_User(name);
    }

    //查所有人
    @Override
    public List<User_Information> Dao_All_Che_User() {
        return iuserdaoimpl.Dao_All_Che_User();
    }
    //查找用户余额
    @Override
    public User_Information Dao_checkbalance(User_Information user) {
        return iuserdaoimpl.Dao_checkbalance(user);
    }
//增加用户余额
    @Override
    public void Dao_Rechar_balance(User_Information user) {
        iuserdaoimpl.Dao_RecharBalance(user);
    }
}
