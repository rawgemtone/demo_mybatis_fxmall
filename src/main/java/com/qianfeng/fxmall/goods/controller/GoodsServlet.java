package com.qianfeng.fxmall.goods.controller;

import com.qianfeng.fxmall.commons.info.SystemConstantsUtils;
import com.qianfeng.fxmall.goods.bean.WxbGood;
import com.qianfeng.fxmall.goods.service.IGoodsService;
import com.qianfeng.fxmall.goods.service.impl.GoodsServiceImpl;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class GoodsServlet extends BaseServlet{
//    private IGoodsService goodsService = new GoodsServiceImpl();
    final ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("Spring.xml");
    private IGoodsService goodsService = applicationContext.getBean(GoodsServiceImpl.class);

    public void queryGoodsByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException{
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

    public void addGoods(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        String good_name = req.getParameter("good_name");
        String type_id = req.getParameter("type_id");
        Long order_no = Long.parseLong(req.getParameter("order_no"));
        Long sell_num = Long.parseLong(req.getParameter("sell_num"));
        String promote_desc = req.getParameter("promote_desc");
        String tags = req.getParameter("tags");
        String copy_desc = req.getParameter("copy_desc");
        String forward_link = req.getParameter("forward_link");
        String gcopy = req.getParameter("gcopy");
        String zcopy_id = req.getParameter("zcopy_id");
        String good_id = UUID.randomUUID().toString().substring(0,8);
        //////////////////////////////////////////////////////
        String sku_title = req.getParameter("sku_title");
        String sku_cost = req.getParameter("sku_cost");
        String sku_pmoney = req.getParameter("sku_pmoney");
        String sku_price = req.getParameter("sku_price");
        String sku_str = req.getParameter("sku_str");
        String service_money = req.getParameter("service_money");
        String copy_id = req.getParameter("copy_id");
        String spc_id = req.getParameter("spc_id");
        String zon_id = req.getParameter("zon_id");
        WxbGood wxbGood = new WxbGood();
        String goodId = UUID.randomUUID().toString().substring(1, 9);
        wxbGood.setGoodId(goodId);
        wxbGood.setCustomerId("123456");
        wxbGood.setSkuTitle("qwe");
        wxbGood.setSkuCost("qwe");
        wxbGood.setSkuPrice("123");
        wxbGood.setSkuPmoney("123");
        wxbGood.setCopyIds("123");
        wxbGood.setState(123);
        Timestamp timestamp = new Timestamp( new Date().getTime());
        wxbGood.setCreateTime(timestamp);
        wxbGood.setToped(123);
        wxbGood.setRecomed(123);
        wxbGood.setTopedTime(timestamp);
        wxbGood.setRecomedTime(timestamp);
        wxbGood.setSpcId("123");
        wxbGood.setZonId("123");
        wxbGood.setWebsite("qwe");
        wxbGood.setIswxpay(123);
        wxbGood.setIsfdfk(123);
        wxbGood.setLeixingId(123);
        wxbGood.setKfqq("aaa");
        if (ServletFileUpload.isMultipartContent(req))
        {
            ServletFileUpload upload = new ServletFileUpload();
            upload.setFileSizeMax(5 * 1024 * 1024);

            // 获得表单项迭代器
            try
            {
                FileItemIterator itr = upload.getItemIterator(req);
                Integer i = 0;
                // 是否一般表单项
                while (itr.hasNext())
                {
                    FileItemStream item = itr.next();
                    // 是否一般表单项
                    if (item.isFormField())
                    {
                        // 读取表单值
                        String value = Streams.asString(item.openStream(),
                                "utf-8");
                        // 用户属性赋值
                        switch (item.getFieldName()) {
                            case "good_name":
                                wxbGood.setGoodName(value);
                                break;
                            case "type_id":
                                wxbGood.setTypeId((value));
                                break;
                            case "order_no":
                                wxbGood.setOrderNo(Long.parseLong(value));
                                break;
                            case "sell_num":
                                wxbGood.setSellNum(Long.parseLong(value));
                                break;
                            case "promote_desc":
                                wxbGood.setPromoteDesc((value));
                                break;
                            case "tags":
                                wxbGood.setTags((value));
                                break;
                            case "copy_desc":
                                wxbGood.setCopyDesc((value));
                                break;
                            case "forward_link":
                                wxbGood.setForwardLink((value));
                                break;
                        }
                    } else
                    {
                        // 获得文件名，进行处理
                        String filename = item.getName();
                        if(filename != null && filename.length()>0){
                            String filename2 = UUID.randomUUID().toString()
                                    + filename.substring(filename.lastIndexOf("."));

                            // 保存新文件名，用于存入数据库
                            if(i == 0){
                                wxbGood.setGoodPic(filename2);
                            }
                            if(i == 1){
                                wxbGood.setGoodPic1(filename2);
                            }
                            if(i == 2){
                                wxbGood.setGoodPic2(filename2);
                            }
                            filename = SystemConstantsUtils.UPLOAD_PATH + filename2;
                            // 创建文件输出流
                            FileOutputStream out = new FileOutputStream(filename);
                            // 读上传文件流，写入文件
                            Streams.copy(item.openStream(), out, true);
                            i++;
                        }
                    }

                }
                goodsService.addGoods(wxbGood);
//                queryGoodsByPage(req,resp);
//                resp.encodeRedirectURL("goods.do?m=queryGoodsByPage");
                req.getRequestDispatcher("goods.do?m=queryGoodsByPage").forward(req,resp);

            } catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }else{
            System.out.println("没进来！");
        }
    }


}
