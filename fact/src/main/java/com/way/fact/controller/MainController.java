package com.way.fact.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台主页面控制器
 * @author Administrator
 */
@RequestMapping(value = {"/main" , "/"})
@Controller
public class MainController {

    @RequestMapping(value = {"/index" , ""})
    public ModelAndView index(ModelAndView view){
        view.setViewName("/main/index");
        return view;
    }

}
