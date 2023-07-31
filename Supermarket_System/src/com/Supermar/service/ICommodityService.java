package com.Supermar.service;

import com.Supermar.domain.Commodity_Information;

import java.util.List;

/**
 * @Author：林杰
 * @Package：com.Supermar.service
 * @Project：IdeaProjects
 * @name：ICommodityService
 * @Date：2023/7/28 8:17
 * @Filename：ICommodityService
 */
public interface ICommodityService {
    //增
    int Add_information(Commodity_Information commodity_information);
    //删
    int Delinformation(Commodity_Information commodity_information);
    //改
    void Upd_information(Commodity_Information commodity_information);
    //查
    List<Commodity_Information> Che_information();
    //查一个
    Commodity_Information One_Che_information(Commodity_Information commodity_information);
    //根据商品名修改商品数量
    void Updin_information(Commodity_Information commodity_information);
}
