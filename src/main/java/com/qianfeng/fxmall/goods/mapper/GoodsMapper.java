package com.qianfeng.fxmall.goods.mapper;

import com.qianfeng.fxmall.goods.bean.WxbGood;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface GoodsMapper {
    List<WxbGood> queryGoodsByPage(@Param("index") int index, @Param("size") int size);
    void addGoods(WxbGood wxbGood);
    WxbGood queryGoodByGoodId(@Param("goodId") String goodId);
}
