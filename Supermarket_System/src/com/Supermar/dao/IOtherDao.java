package com.Supermar.dao;

import com.Supermar.domain.Collection;
import com.Supermar.domain.History;
import com.Supermar.domain.Shopping;
import com.Supermar.domain.User_Information;

import java.util.List;

/**
 * @Author：林杰
 * @Package：com.Supermar.dao
 * @Project：IdeaProjects
 * @name：IOtherDao
 * @Date：2023/7/28 8:18
 * @Filename：IOtherDao
 */
public interface IOtherDao {
    //购物车增加商品
    void Dao_Add_Shopping(Shopping shop);
    //购物车删除商品
    void Dao_Del_Shopping(Shopping shop);
    //改购物车商品
    void Dao_Upd_Shopping(Shopping shop);
    //查购物车商品，一次查询所有
    List<Shopping> Dao_Che_History();

    //历史记录
    //历史记录增加商品，一次添加一个
    void Dao_Add_History(History history);
    //历史查询商品，一次查询所有
    List<History> Dao_Che_Shopping();

    //购物完，删除购物车商品
    void Dao_Del_Shopping_Uid(Shopping shop);

    // 添加收藏
    void Dao_Add_collection(Collection collection);
    // 删除商品
    void Dao_Delete_collection(Collection collection);
    //查询收藏
    List<Collection> Dao_Che_Collection(Collection collection);

}
