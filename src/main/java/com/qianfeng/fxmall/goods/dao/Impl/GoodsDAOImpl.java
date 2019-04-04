package com.qianfeng.fxmall.goods.dao.Impl;

import com.qianfeng.fxmall.commons.batis.MybatisSessionFactoryUtils;
import com.qianfeng.fxmall.commons.info.SystemConstantsUtils;
import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.dao.IGoosDAO;
import com.qianfeng.fxmall.goods.mapper.GoodsMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
/*将session注入到里面*/
@Component
public class GoodsDAOImpl
        implements IGoosDAO {
    @Autowired
    private SqlSession session;
    @Override
    public List<WxbGood> queryGoodsByPage(Integer page) throws IOException {
        GoodsMapper mapper = session.getMapper(GoodsMapper.class);
       List<WxbGood> goods = mapper.queryGoodsByPage(page,SystemConstantsUtils.page.PAGE_SIZE);
        return goods;
    }

    @Override
    public void addGoods(WxbGood wxbGood) {
        GoodsMapper mapper = session.getMapper(GoodsMapper.class);
        mapper.addGoods(wxbGood);
    }
}
