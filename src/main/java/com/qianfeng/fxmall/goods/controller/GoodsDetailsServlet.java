package com.qianfeng.fxmall.goods.controller;

import com.qianfeng.fxmall.commons.utils.ApplicationContextUtils;
import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.service.ISpringGoodsService;
import com.qianfeng.fxmall.goods.service.impl.SpringGoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoodsDetailsServlet extends HttpServlet {
    private ISpringGoodsService goodsService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String goodId = req.getParameter("goodId");
        goodsService = ApplicationContextUtils.getApplicationContext().getBean(SpringGoodsServiceImpl.class);
        WxbGood wxbGood = goodsService.queryGoodsByGoodid("goodId");
        req.getRequestDispatcher("addgoods.jsp").forward(req,resp);
    }
}
