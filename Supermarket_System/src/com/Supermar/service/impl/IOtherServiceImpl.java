package com.Supermar.service.impl;

import com.Supermar.dao.impl.IOtherDaoImpl;
import com.Supermar.dao.impl.IUserDaoImpl;
import com.Supermar.domain.Collection;
import com.Supermar.domain.History;
import com.Supermar.domain.Shopping;
import com.Supermar.service.IOtherService;

import java.util.List;

/**
 * @Author：林杰
 * @Package：com.Supermar.service.impl
 * @Project：IdeaProjects
 * @name：IOtherServiceImpl
 * @Date：2023/7/28 19:06
 * @Filename：IOtherServiceImpl
 */
public class IOtherServiceImpl implements IOtherService {
    //用户类的业务处理层

    //创建一个dao包的接口对象
    private IOtherDaoImpl iotherdaoimpl;
    //用构造器，获取对象
    public IOtherServiceImpl() {
        //父类接口让子类实现,父类引用变量指向子类对象
        //解释：这样service包可以直接通过dao包的接口使用其子类实现的功能方法
        iotherdaoimpl = new IOtherDaoImpl();
    }
    @Override
    public void Add_Shopping(Shopping shop) {
        iotherdaoimpl.Dao_Add_Shopping(shop);
    }

    @Override
    public void Del_Shopping(Shopping shop) {
        iotherdaoimpl.Dao_Del_Shopping(shop);
    }

    @Override
    public void Upd_Shopping(Shopping shop) {
        iotherdaoimpl.Dao_Upd_Shopping(shop);
    }

    @Override
    public List<Shopping> Che_History() {
        return iotherdaoimpl.Dao_Che_History();
    }

    @Override
    public void Dao_Add_History(History history) {
        iotherdaoimpl.Dao_Add_History(history);
    }

    @Override
    public List<History> Che_Shopping() {
        return iotherdaoimpl.Dao_Che_Shopping();
    }

    @Override
    public void Del_Shopping_Uid(Shopping shop) {
        iotherdaoimpl.Dao_Del_Shopping_Uid(shop);
    }
    //添加收藏
    @Override
    public void Dao_addCollection(Collection collection) {
        iotherdaoimpl.Dao_Add_collection(collection);
    }
    @Override
    public void Dao_DeleteCollection(Collection collection) {
        iotherdaoimpl.Dao_Delete_collection(collection);
    }

    //显示收藏列表
    @Override
    public List<Collection> Che_Collection(Collection collection) {
        return iotherdaoimpl.Dao_Che_Collection(collection);
    }

}
