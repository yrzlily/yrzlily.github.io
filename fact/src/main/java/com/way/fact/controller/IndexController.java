package com.way.fact.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前端页面
 * @author yrz
 */
@RestController
@RequestMapping(value = {"/" , "/index"})
public class IndexController {

    @RequestMapping(value = {"/index" , ""})
    public String index(){

        return "index";
    }

}
