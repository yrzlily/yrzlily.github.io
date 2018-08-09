package com.way.fact.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yrz
 */
@Aspect
@Component
public class AopConfig {

    private static final Logger log = LoggerFactory.getLogger(AopConfig.class);

    /**
     * 切点
     */
    @Pointcut("execution(* com.way.fact.controller.*Controller.*(..))")
    public void point(){

    }

    /**
     * 日志打印
     * @param joinPoint
     */
    @Before("point()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
        log.info("URL = {}",request.getRequestURL().toString());
        //传输类型
        log.info("HTTP_METHOD = {}",request.getMethod());
        //IP
        log.info("IP = {}" , request.getRemoteAddr());
        //类方法
        log.info("CLASS_METHOD = {}",joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //参数
        log.info("Args = {}",joinPoint.getArgs());
    }

    @After("point()")
    public void doAfter(){
        log.info("end");
    }

    @AfterReturning(returning = "object" , pointcut = "point()")
    public void doAfterReturning(Object object){
        log.info("RESPONSE = {}",object.toString());
    }

}
