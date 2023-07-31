package com.Supermar.dao.impl;

import com.Supermar.dao.IUserDao;
import com.Supermar.domain.User_Information;
import com.Supermar.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author：林杰
 * @Package：com.Supermar.dao.impl
 * @Project：IdeaProjects
 * @name：IUserDaoImpl
 * @Date：2023/7/28 18:54
 * @Filename：IUserDaoImpl
 */
public class IUserDaoImpl implements IUserDao {
    QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
    User_Information user = new User_Information();
    List<User_Information>user_informationList=null;
    //增一个用户
    @Override
    public void Dao_Add_User(User_Information user) {
        String sql = "Insert into User_Information value (?,?,?,?)";
        Object[] param = {user.getUid(),user.getAccount(),user.getPassword(),user.getBalance()};
        int a = -1;
        try {
            a = qr.update(sql,param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (a==1){
            System.out.println("新增用户信息成功！0.5秒后回到管理员权限页面");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("QAQ~~新增用户失败~~");
        }

    }

    //删一个用户，根据Uid删除
    @Override
    public void Dao_Del_User(User_Information user) {
        String sql="delete from user_information where Uid=?";
        Object[]params={user.getUid()};
        try {
            qr.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //改一个用户，根据Uid修改
    @Override
    public void Dao_Upd_User(User_Information user) {
        String sql="update  user_information set Account=? ,Password=?where Uid=?";
        Object[]params={user.getAccount(),user.getPassword(),user.getUid()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //查一个人，根据Uid寻找
    @Override
    public User_Information Dao_Che_User(User_Information user) {
        return null;
    }
    //查所有人，不需要条件筛选，所以不需要参数
    @Override
    public List<User_Information> Dao_All_Che_User() {
        String sql="select*from user_information ";
        BeanListHandler<User_Information> bl=new BeanListHandler<>(User_Information.class);

        try {
            user_informationList=qr.query(sql,bl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_informationList;
    }

    //根据用户名查询
    @Override
    public User_Information Dao_name_User(String name) {
        String sql = "select * from user_information where Account = ?";
        Object param = name;
        BeanHandler<User_Information> bh = new BeanHandler<>(User_Information.class);
        try {
            user = qr.query(sql,bh,param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    //查询用户余额
    @Override
    public User_Information Dao_checkbalance(User_Information user) {
        String sql ="select * from user_information where Uid =?";
        BeanHandler<User_Information> blh  = new BeanHandler<>(User_Information.class);
        Object param =user.getUid();
        try {
            user = qr.query(sql,blh,param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }
    //修改用户余额
    @Override
    public void Dao_RecharBalance(User_Information user_information ) {
        String sql="update user_information set Balance=? where Account =?";
        Object[] parm = {user_information.getBalance(),user_information.getAccount()};
        int a =0;
        try {
            a = qr.update(sql,parm);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
