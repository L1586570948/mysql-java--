package com.Supermar.service;

import com.Supermar.domain.Collection;
import com.Supermar.domain.History;
import com.Supermar.domain.Shopping;

import java.util.List;

/**
 * @Author：林杰
 * @Package：com.Supermar.service
 * @Project：IdeaProjects
 * @name：IOtherService
 * @Date：2023/7/28 8:18
 * @Filename：IOtherService
 */
public interface IOtherService {
    //购物车增加商品
    void Add_Shopping(Shopping shop);
    //购物车删除商品
    void Del_Shopping(Shopping shop);
    //改购物车商品
    void Upd_Shopping(Shopping shop);
    //查购物车商品
    List<Shopping> Che_History();

    //历史记录
    //历史记录增加商品
    void Dao_Add_History(History history);
    //历史记录商品
    List<History> Che_Shopping();
    //购买完，删除购物车所有商品
    void Del_Shopping_Uid(Shopping shop);

    //添加收藏
    void Dao_addCollection(Collection collection);
    //删除收藏
    void Dao_DeleteCollection(Collection collection);
    //显示收藏列表
    List<Collection> Che_Collection(Collection collection);
}
