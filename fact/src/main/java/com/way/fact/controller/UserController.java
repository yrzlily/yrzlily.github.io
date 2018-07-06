package com.way.fact.controller;

import com.way.fact.bean.Result;
import com.way.fact.bean.User;
import com.way.fact.dao.UserDao;
import com.way.fact.enums.UserEnum;
import com.way.fact.service.UserService;
import com.way.fact.utils.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * @author Administrator
 */
@RequestMapping(value = {"/user"})
@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    /**
     * 用户首页
     * @return
     */
    @RequestMapping(value = {"/index"})
    public String index(){

        User user = (User) SecurityUtils.getSubject().getPrincipal();
        log.info("user_id = {}" , user.getId());
        return "用户首页";
    }

    /**
     * 退出登陆
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        return "退出";
    }

    /**
     * 用户列表
     * @return
     */
    @PostMapping("/list")
    public Result list(@PageableDefault(size = 1)Pageable pageable , @RequestParam("username") String username ){

        Page<User> list = userService.findAll(pageable , username);
        return ResultUtils.success(list);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Object add(@Valid User user , BindingResult bindingResult){

        //判断是否有误
        if(bindingResult.hasErrors()){
            return ResultUtils.error(10001 , bindingResult.getFieldError().getDefaultMessage());
        }

        //MD5加密
        String salt = new SimpleHash("MD5" , user.getPassword() , null , 1024).toString();

        //添加操作
        user.setUsername(user.getUsername());
        user.setPassword(salt);
        user.setStatus((long) 0);
        userDao.save(user);
        return ResultUtils.success(user);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable int id){
        Object user = userDao.findById(id);

        userDao.deleteById(id);
        return ResultUtils.success(user);
    }

}
