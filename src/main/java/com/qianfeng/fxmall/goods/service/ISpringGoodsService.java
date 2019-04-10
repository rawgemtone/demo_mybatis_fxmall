package com.qianfeng.fxmall.goods.service;

import com.qianfeng.fxmall.goods.bean.WxbGood;

public interface ISpringGoodsService {
    WxbGood queryGoodsByGoodid(String goodId);
}
