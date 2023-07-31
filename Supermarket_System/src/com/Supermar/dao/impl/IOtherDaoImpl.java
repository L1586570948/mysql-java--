package com.Supermar.dao.impl;

import com.Supermar.dao.IOtherDao;
import com.Supermar.domain.Collection;
import com.Supermar.domain.History;
import com.Supermar.domain.Shopping;
import com.Supermar.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author：林杰
 * @Package：com.Supermar.dao.impl
 * @Project：IdeaProjects
 * @name：IOtherDaoImpl
 * @Date：2023/7/28 18:55
 * @Filename：IOtherDaoImpl
 */
public class IOtherDaoImpl implements IOtherDao {
    QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());

    //购物车增加商品，一次添加一个
    @Override
    public void Dao_Add_Shopping(Shopping shop) {
        String  sql="insert into shopping(Uid,Cname,Cprice,number)values(?,?,?,?)";
        Object []params = {shop.getUid(),shop.getCname(),shop.getCprice(),shop.getNumber()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //购物车删除商品，根据购物车的名字Cname，一次删除一个
    @Override
    public void Dao_Del_Shopping(Shopping shop) {
        String sql ="delete from Shopping where Cname = ? ";
        Object param = shop.getCname();
        try {
            qr.update(sql,param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //改购物车商品，根据购物车的名字Cname，一次修改一个，修改商品个数number，如果个数为0那么就从购物车表删除该商品。
    @Override
    public void Dao_Upd_Shopping(Shopping shop) {
        String sql = "update  shopping  set number=? where Cname=?";
        Object[] params = {shop.getNumber(),shop.getCname()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //查购物车商品，打印所有的购物车商品，所以不需要参数
    @Override
    public List<Shopping> Dao_Che_History() {
        String sql ="select* from shopping";
        BeanListHandler<Shopping>blh = new BeanListHandler<>(Shopping.class);
        List<Shopping>shoppingList = null;
        try {
            shoppingList=qr.query(sql,blh);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingList;
    }
    //历史记录
    //历史记录增加商品，一次添加一个
    @Override
    public void Dao_Add_History(History history) {
        String sql ="insert into history(Uid,Cname,Browernow)values(?,?,?)  ";
        Object[] params={history.getUid(),history.getCname(),history.getBrowernow()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //历史查询商品，打印所有的历史记录，所以不需要参数
    @Override
    public List<History> Dao_Che_Shopping() {
        String sql ="select*from history";
        BeanListHandler<History>blh = new BeanListHandler<>(History.class);
        List<History>historyList =null;
        try {
            historyList=qr.query(sql,blh);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historyList;
    }
    //清空购物车
    @Override
    public void Dao_Del_Shopping_Uid(Shopping shop) {
        String sql ="delete from shopping where Uid=?";
        Object param=shop.getUid();
        try {
            qr.update(sql,param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //添加收藏
    @Override
    public void Dao_Add_collection(Collection collection) {
        int a = 0;
        String sql = "insert into Collection(Uid,Type,Cname,Cprice,Caddtime) values(?,?,?,?,?)";
        Object[] param = {collection.getUid(), collection.getType(), collection.getCname(), collection.getCprice(), collection.getCaddtime()};
        try {
            a = qr.update(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (a != 0) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }
    }

    //删除收藏
    @Override
    public void Dao_Delete_collection(Collection collection) {
        int a = 0;
        String sql = "delete from collection where Cname =? and Uid = ?";
        Object[] param = {collection.getCname(),collection.getUid()};
        try {
            a = qr.update(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (a != 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }
    }

    //查询收藏
    @Override
    public List<Collection> Dao_Che_Collection(Collection collection) {
        String sql = "select * from collection where Uid = ?";
        Object params = collection.getUid();
        BeanListHandler<Collection> blh = new BeanListHandler<>(Collection.class);
        List<Collection> shoppingList = null;
        try {
            shoppingList = qr.query(sql, blh,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingList;
    }
}
