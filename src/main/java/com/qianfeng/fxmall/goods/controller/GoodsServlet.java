package com.qianfeng.fxmall.goods.controller;

import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.service.IGoodsService;
import com.qianfeng.fxmall.goods.service.impl.GoodsServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GoodsServlet extends HttpServlet {
//    private IGoodsService goodsService = new GoodsServiceImpl();
    final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
    private IGoodsService goodsService = applicationContext.getBean(GoodsServiceImpl.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
       String pagestr = (String) req.getAttribute("page");
        try {
            pagestr = pagestr==null?"1":pagestr;
           List<WxbGood> goodList =
                   goodsService.queryGoodsByPage(Integer.parseInt(pagestr));
        req.setAttribute("goodsList",goodList);
        req.getRequestDispatcher("goods_list.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
