package com.fact.task.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * aop配置
 * @author yrz
 */
@Aspect
@Component
public class AopConfig {

    private static final Logger log = LoggerFactory.getLogger(AopConfig.class);

    @Pointcut("execution(* com.fact.task.controller.*Controller.*(..))")
    public void cutPoint(){

    }

    /**
     * 请求信息详情
     * @param joinPoint
     */
    @Before("cutPoint()")
    public void before(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
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

}
