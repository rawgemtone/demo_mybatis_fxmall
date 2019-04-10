package com.qianfeng.fxmall.goods.service.impl;

import com.qianfeng.fxmall.commons.info.SystemConstantsUtils;
import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.dao.IGoosDAO;
import com.qianfeng.fxmall.goods.service.IGoodsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class GoodsServiceImpl implements IGoodsService {
    private Logger logger = Logger.getLogger(GoodsServiceImpl.class);
    @Autowired
    private IGoosDAO goosDAO;
    @Override
    public List<WxbGood> queryGoodsByPage(Integer page) throws IOException {
        logger.debug("queryGoodsByPage="+page);
        if (page < 1 ){
            logger.warn("page<1");
            throw new IndexOutOfBoundsException("页码不能小于1");
        }
        //计算其实下标
        int index = (page -1)* SystemConstantsUtils.page.PAGE_SIZE;
      List<WxbGood> goods = goosDAO.queryGoodsByPage(index);
      if (goods != null && !goods.isEmpty()){
          StringBuilder builder = new StringBuilder();
          for (WxbGood good:goods){
              builder.append(good.getGoodName()+",");

          }
          logger.debug("queryGoodsByPage,return :"+builder.toString());
      }

        return goods;
    }

    @Override
    public void addGoods(WxbGood wxbGood) {
        goosDAO.addGoods(wxbGood);
    }
}
