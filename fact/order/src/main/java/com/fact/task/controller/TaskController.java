package com.fact.task.controller;

import com.fact.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 线程池连接
 * @author yrz
 */
@RequestMapping("/task")
@RestController
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskService taskService;

    @RequestMapping("/")
    public String submit(){

        //调用service层的任务
        taskService.executeAsync(1);

        return "success";
    }



}
