package com.way.fact.controller;

import com.way.fact.bean.Result;
import com.way.fact.bean.Role;
import com.way.fact.bean.User;
import com.way.fact.dao.UserDao;
import com.way.fact.service.UserService;
import com.way.fact.utils.ResultUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    @GetMapping(value = {"/index"})
    public ModelAndView index(ModelAndView view){
        view.setViewName("/user/index");
        return view;
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
    @GetMapping("/list")
    public Object list(Pageable pageable , @RequestParam(value = "username" , required = false) String username ){
        pageable = PageRequest.of(pageable.getPageNumber()-1 , pageable.getPageSize());
        log.info("limit = {}" , pageable);
        Page<User> list = userService.findAll(pageable , username);

        return ResultUtils.layPage(0 ,"" , list.getTotalPages() , list.getContent());
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    public Object add(
            @Valid User user ,
            @RequestParam(value = "role" , required = false) List<Role> role,
            BindingResult bindingResult){

        //判断是否有误
        if(bindingResult.hasErrors()){
            return ResultUtils.error(10001 , bindingResult.getFieldError().getDefaultMessage());
        }

        //MD5加密
        String salt = new SimpleHash("MD5" , user.getPassword() , null , 1024).toString();

        //添加操作
        user.setUsername(user.getUsername());
        user.setPassword(salt);
        user.setPhone(user.getPhone());
        user.setStatus((long) 0);
        user.setRoles(role);
        userDao.save(user);
        return ResultUtils.success(user);
    }

    /**
     * 编辑用户
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping("/edit")
    public Result edit(
            @Valid User user ,
            BindingResult bindingResult
            ){
        if(bindingResult.hasErrors()){
            return ResultUtils.error(10001 , bindingResult.getFieldError().getDefaultMessage());
        }

        //MD5加密
        String salt = new SimpleHash("MD5" , user.getPassword() , null , 1024).toString();

        //添加操作
        user.setId(user.getId());
        user.setUsername(user.getUsername());
        user.setPassword(salt);
        user.setPhone(user.getPhone());
        user.setStatus(user.getStatus());
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

    /**
     * 用户详情
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    public Result details(@PathVariable Integer id){
        Optional<User> user = userDao.findById(id);

        return ResultUtils.success(user);
    }

}
