package com.way.fact.controller;

import com.way.fact.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 后台主页面
 * @author Administrator
 */
@RequestMapping(value = {"/main" , "/admin"})
@Controller
//@RequiresRoles("admin")
public class MainController {

    /**
     * 后台主页面视图
     * @param view
     * @return
     */
    @RequestMapping(value = {"/index" , ""})
    public ModelAndView index(ModelAndView view){

        view.setViewName("/main/index");
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        view.addObject("user" , user);
        return view;
    }

}
