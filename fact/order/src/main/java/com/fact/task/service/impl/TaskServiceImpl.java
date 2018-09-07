package com.fact.task.service.impl;

import com.fact.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author yrz
 */
@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Async("executor")
    public void executeAsync(int i) {
        try{
            Thread.sleep(1000);
            logger.info("执行任务{}次" , i);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
