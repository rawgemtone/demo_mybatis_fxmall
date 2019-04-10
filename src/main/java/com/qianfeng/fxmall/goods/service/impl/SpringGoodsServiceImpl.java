package com.qianfeng.fxmall.goods.service.impl;

import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.mapper.GoodsMapper;
import com.qianfeng.fxmall.goods.service.ISpringGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

public class SpringGoodsServiceImpl implements ISpringGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public WxbGood queryGoodsByGoodid(String goodId) {
    WxbGood goods = goodsMapper.queryGoodByGoodId(goodId);
    return goods;
    }
}
