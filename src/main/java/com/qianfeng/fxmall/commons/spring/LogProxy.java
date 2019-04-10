package com.qianfeng.fxmall.commons.spring;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogProxy {
    /*
    * joinpoint切点
    * @param joinpoint
    * */
    Logger logger = Logger.getLogger(LogProxy.class);
    @Around("execution(public * com.qianfeng.fxmall.goods.service.impl.SpringGoodsServiceImpl.*(..))")
    public void logging(ProceedingJoinPoint joinPoint){
        try {
            String kind =joinPoint.getKind();
            //方法的入参
            Object[] args = joinPoint.getArgs();
            //记录日志
            logger.debug(kind+":"+args);
            Object procced = joinPoint.proceed();
            logger.debug("返回值："+procced);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            logger.warn(throwable.getMessage());
        }
    }
}
