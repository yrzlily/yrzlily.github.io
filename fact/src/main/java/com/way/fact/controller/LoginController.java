package com.way.fact.controller;


import com.way.fact.bean.Result;
import com.way.fact.bean.User;
import com.way.fact.utils.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author yrz
 */
@RequestMapping("/login")
@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    /**
     * @return 登陆请求
     */
    @PostMapping("/check")
    @ResponseBody
    public Result login(@RequestBody User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername() , user.getPassword());
        token.setRememberMe(true);
        String msg;
        try {
            subject.login(token);
            log.info("token = {}" , token);
            return ResultUtils.success(token);
        }catch (UnknownAccountException e) {
            msg = "用户账号不存在";
        } catch (LockedAccountException e) {
            msg = "用户账号被锁定";
        } catch (DisabledAccountException e) {
            msg = "用户账号被禁用";
        } catch (IncorrectCredentialsException e) {
            msg = "用户密码错误";
        }
        log.info("登录状态：{}" , msg);

        return ResultUtils.error(10000 , msg);
    }

    /**
     * @return 退出登陆
     */
    @RequestMapping("/logout")
    public String logout(){
        return "/login/index";
    }

    /**
     * @return 未登录跳转页面
     */
    @RequestMapping("/index")
    public ModelAndView index(ModelAndView view){

        User user = (User) SecurityUtils.getSubject().getPrincipal();

        if(user==null){
            view.setViewName("/login/index");
        }else{
            view.setViewName("/admin");
        }


        return view;
    }

    /**
     * @return 未授权跳转页面
     */
    @RequestMapping("/unAuth")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
}
