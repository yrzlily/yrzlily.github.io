package com.way.fact.controller;


import com.way.fact.bean.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Administrator
 */
@RequestMapping("/login")
@RestController
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    /**
     * 登陆请求
     * @param user
     * @return
     */
    @RequestMapping("/check")
    public String login(@Valid User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
        String msg;
        try {
            token.setRememberMe(true);
            subject.login(token);
            log.info("token = {}" , token);
            msg = "用户登录成功";
        }catch (UnknownAccountException e) {
            msg = "用户账号未登录";
        } catch (LockedAccountException e) {
            msg = "用户账号被锁定";
        } catch (DisabledAccountException e) {
            msg = "用户账号被禁用";
        } catch (IncorrectCredentialsException e) {
            msg = "用户密码错误";
        }
        log.info("登录状态：{}" , msg);

        return "login";
    }

    /**
     * 默认登陆页面
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    /**
     * 未授权跳转页面
     * @return
     */
    @RequestMapping("/unAuth")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
}
