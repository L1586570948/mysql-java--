package com.Supermar.service.impl;

import com.Supermar.dao.impl.ICommodityDaoImpl;
import com.Supermar.domain.Commodity_Information;
import com.Supermar.service.ICommodityService;

import java.util.List;

/**
 * @Author：林杰
 * @Package：com.Supermar.service.impl
 * @Project：IdeaProjects
 * @name：ICommodityServiceImpl
 * @Date：2023/7/28 18:56
 * @Filename：ICommodityServiceImpl
 */
public class ICommodityServiceImpl implements ICommodityService {

    //用户类的业务处理层

    //创建一个dao包的接口对象
    private ICommodityDaoImpl icommoditydaoimpl;
    //用构造器，获取对象
    public ICommodityServiceImpl() {
        //父类接口让子类实现,父类引用变量指向子类对象
        //解释：这样service包可以直接通过dao包的接口使用其子类实现的功能方法
        icommoditydaoimpl = new ICommodityDaoImpl();
    }
    @Override
    public int Add_information(Commodity_Information commodity_information) {
        icommoditydaoimpl.Dao_Add_information(commodity_information);
        return 0;
    }

    @Override
    public int Delinformation(Commodity_Information commodity_information) {
        icommoditydaoimpl.Dao_Delinformation(commodity_information);
        return 0;
    }

    @Override
    public void Upd_information(Commodity_Information commodity_information) {
        icommoditydaoimpl.Dao_Upd_information(commodity_information);
    }

    @Override
    public List<Commodity_Information> Che_information() {
        return icommoditydaoimpl.Dao_Che_information();
    }

    @Override
    public Commodity_Information One_Che_information(Commodity_Information commodity_information) {
        return icommoditydaoimpl.Dao_One_Che_information(commodity_information);
    }

    @Override
    public void Updin_information(Commodity_Information commodity_information) {
        icommoditydaoimpl.Dao_Updin_information(commodity_information);
    }
}
