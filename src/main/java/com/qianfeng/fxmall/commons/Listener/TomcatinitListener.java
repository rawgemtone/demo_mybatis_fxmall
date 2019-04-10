package com.qianfeng.fxmall.commons.Listener;

import com.qianfeng.fxmall.commons.utils.ApplicationContextUtils;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TomcatinitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       ApplicationContextUtils.init();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
