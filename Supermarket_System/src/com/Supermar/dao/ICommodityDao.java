package com.Supermar.dao;

import com.Supermar.domain.Commodity_Information;
import com.Supermar.domain.User_Information;

import java.util.List;

/**
 * @Author：林杰
 * @Package：com.Supermar.dao
 * @Project：IdeaProjects
 * @name：ICommodityDao
 * @Date：2023/7/28 8:17
 * @Filename：ICommodityDao
 */
public interface ICommodityDao {
    //增
    void Dao_Add_information(Commodity_Information commodity_information);
    //删
    void Dao_Delinformation(Commodity_Information commodity_information);
    //改
    void Dao_Upd_information(Commodity_Information commodity_information);
    //查所有
    List<Commodity_Information> Dao_Che_information();
    //查一个
    Commodity_Information Dao_One_Che_information(Commodity_Information commodity_information);
    //根据商品名修改商品数量
    void Dao_Updin_information(Commodity_Information commodity_information);
}
