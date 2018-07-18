package com.way.fact.controller;

import com.way.fact.bean.Result;
import com.way.fact.bean.Role;
import com.way.fact.bean.User;
import com.way.fact.dao.RoleDao;
import com.way.fact.dao.UserDao;
import com.way.fact.service.UserService;
import com.way.fact.service.impl.UserServiceImpl;
import com.way.fact.utils.ResultUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 */
@RequestMapping(value = {"/user"})
@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserService userService;

    /**
     * 用户首页视图
     * @return
     */
    @GetMapping(value = {"/index"})
    public ModelAndView index(ModelAndView view , HttpServletRequest request){
        view.setViewName("/user/index");
        return view;
    }

    /**
     * 添加用户视图
     * @param view
     * @return
     */
    @GetMapping("/add")
    public ModelAndView add(ModelAndView view){
        //权限信息
        List<Role> role = roleDao.findAll();
        view.addObject("role" , role);

        view.setViewName("/user/add");
        return view;
    }

    /**
     * 用户编辑视图
     * @param id
     * @param view
     * @return
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Integer id , ModelAndView view){
        //用户信息
        User user = userDao.findById(id).get();
        view.addObject("user" , user);
        //权限信息
        List<Role> role = roleDao.findAll();
        view.addObject("role" , role);

        view.setViewName("/user/edit");
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
    @ResponseBody
    @GetMapping("/list")
    public Object list(Pageable pageable , @RequestParam(value = "username" , required = false) String username ){
        pageable = PageRequest.of(pageable.getPageNumber()-1 , pageable.getPageSize());
        log.info("limit = {}" , pageable);
        Page<User> list = userService.findAll(pageable , username);

        return ResultUtils.layPage( list.getTotalPages() , list.getContent());
    }


    /**
     * 添加用户
     * @param user
     * @return
     */
    @ResponseBody
    @PostMapping("/add")
    public Object add(
            @Valid User user ,
            BindingResult bindingResult){

        //判断是否有误
        if(bindingResult.hasErrors()){
            return ResultUtils.error(1001 , bindingResult.getFieldError().getDefaultMessage());
        }

        //MD5加密
        String salt = new SimpleHash("MD5" , user.getPassword() , null , 1024).toString();

        //添加操作
        user.setAvatar(user.getAvatar());
        user.setUsername(user.getUsername());
        user.setPassword(salt);
        user.setPhone(user.getPhone());
        user.setStatus(user.getStatus());
        userDao.save(user);
        return ResultUtils.success(user);
    }

    /**
     * 编辑用户
     * @param user
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @PostMapping("/edit")
    public Result edit(
            User user ,
            BindingResult bindingResult
            ){
        if(bindingResult.hasErrors()){
            return ResultUtils.error(1001 , bindingResult.getFieldError().getDefaultMessage());
        }

        //MD5加密
        String salt;
        if(!user.getPassword().isEmpty()) {
            salt = new SimpleHash("MD5", user.getPassword(), null, 1024).toString();
        }else{
            User mess = userDao.getOne(user.getId());
            salt = mess.getPassword();
        }

        //添加操作
        user.setId(user.getId());
        user.setUsername(user.getUsername());
        user.setAvatar(user.getAvatar());
        user.setPassword(salt);
        user.setPhone(user.getPhone());
        user.setStatus(1L);
        userDao.save(user);

        return ResultUtils.success(user);
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @ResponseBody
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
