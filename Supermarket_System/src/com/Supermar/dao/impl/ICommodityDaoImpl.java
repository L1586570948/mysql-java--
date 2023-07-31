package com.Supermar.dao.impl;

import com.Supermar.dao.ICommodityDao;
import com.Supermar.domain.Commodity_Information;
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
 * @name：ICommodityDaoImpl
 * @Date：2023/7/28 18:52
 * @Filename：ICommodityDaoImpl
 */
public class ICommodityDaoImpl implements ICommodityDao {
    private QueryRunner qr = new QueryRunner(DruidUtils.getDataSource());
    //增加一个商品
    @Override
    public void Dao_Add_information(Commodity_Information commodity_information) {
        String sql = "insert into commodity_information(Cid,Cname,Cprice,Type,Cinventory)values(?,?,?,?,?)";
        Object[] params = {commodity_information.getCid(),commodity_information.getCname(),commodity_information.getCprice(),commodity_information.getType(),commodity_information.getCinventory()};
        int a =-1;
        try {
            a=qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (a==1){
            System.out.println("新增商品信息成功！三秒后回到管理员权限页面");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("QAQ~~新增商品失败~~");
        }

    }
    //根据商品编号Cid，一次删除一个
    @Override
    public void Dao_Delinformation(Commodity_Information commodity_information) {
        String sql="delete from commodity_information where Cid=?";
        Object param = commodity_information.getCid();
        try {
            qr.update(sql,param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //根据商品编号Cid，一次修改一个
    @Override
    public void Dao_Upd_information(Commodity_Information commodity_information) {
        String sql ="update Commodity_information set Cprice=?,Cinventory=? where Cid=? ";
        Object []params = {commodity_information.getCprice(),commodity_information.getCinventory(),commodity_information.getCid()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //查，打印所有的商品,不需要参数
    @Override
    public List<Commodity_Information> Dao_Che_information() {
        String sql="select*from commodity_information ";
        BeanListHandler<Commodity_Information> blh = new BeanListHandler<>(Commodity_Information.class);
        List<Commodity_Information>commodity_informationList=null;
        try {
            commodity_informationList=qr.query(sql,blh);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commodity_informationList;
    }
    //查，打印一个商品，根据Cid查询
    @Override
    public Commodity_Information Dao_One_Che_information(Commodity_Information commodity_information) {
        String sql="select*from commodity_information where Cid=?";
        Object param = commodity_information.getCid();
        BeanHandler<Commodity_Information> bh = new BeanHandler<>(Commodity_Information.class);
        Commodity_Information commodityInformation = null;
        try {
            commodityInformation=qr.query(sql,bh,param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commodityInformation;
    }
    //根据商品名修改商品数量
    @Override
    public void Dao_Updin_information(Commodity_Information commodity_information) {
        String sql ="update Commodity_information set Cinventory=? where Cname=? ";
        Object[] params ={commodity_information.getCname(),commodity_information.getCinventory()};
        try {
            qr.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
