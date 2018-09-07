package com.way.fact.controller;

import com.way.fact.service.AsyncTaskService;
import com.way.fact.service.impl.AsyncTaskServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 线程池控制器
 * @author yrz
 */
@RequestMapping("/task")
@RestController
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private AsyncTaskService asyncTaskService;

    @RequestMapping("/index")
    public String index(){

        for (int i = 0; i < 10 ; i++) {
            asyncTaskService.asyncTask(i);
        }


        return "index";
    }

}
