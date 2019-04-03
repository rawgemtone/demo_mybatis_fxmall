package com.qianfeng.fxmall.goods.dao;

import com.qianfeng.fxmall.goods.bean.WxbGood;

import java.io.IOException;
import java.util.List;

/*
* 商品管理
* */
public interface IGoosDAO {
    List<WxbGood> queryGoodsByPage(Integer page) throws IOException;
    /*List<WxbGood> queryAllGoods*/;
}
