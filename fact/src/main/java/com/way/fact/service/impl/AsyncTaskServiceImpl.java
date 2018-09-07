package com.way.fact.service.impl;

import com.way.fact.service.AsyncTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 线程池测试
 * @author yrz
 */
@Service
public class AsyncTaskServiceImpl implements AsyncTaskService {

    private static final Logger log = LoggerFactory.getLogger(AsyncTaskServiceImpl.class);

    @Override
    @Async("asyncServiceExecutor")
    public void asyncTask(int i){
        try{
            Thread.sleep(10000);
            System.out.println("等待中");
            log.info("启动了线程池{}",i);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    @Async("asyncServiceExecutor")
    public void stops(){

    }

}
