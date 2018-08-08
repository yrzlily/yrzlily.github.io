package com.cloud.ribbon.controller;

import com.cloud.ribbon.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 * @author yrz
 */
@RequestMapping("/hello")
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/index")
    public String index(@RequestParam("name")String name){
        return helloService.index(name);
    }

}
