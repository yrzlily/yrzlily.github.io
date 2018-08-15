package com.way.fact.controller;

import com.way.fact.bean.Result;
import com.way.fact.bean.Role;
import com.way.fact.dao.RoleDao;
import com.way.fact.service.RoleService;
import com.way.fact.service.impl.RoleServiceImpl;
import com.way.fact.utils.ResultUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Objects;

/**
 * 管理权限控制层
 * @author yrz
 */
@RequiresRoles("admin")
@RequestMapping(value = "/role")
@Controller
public class RoleController{

    private RoleDao roleDao;

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleServiceImpl roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    /**
     *
     * @param view 视图
     * @return 权限列表视图
     */
    @GetMapping("/index")
    public ModelAndView index(ModelAndView view){
        view.setViewName("/role/index");
        return view;
    }

    /**
     *
     * @param view 视图
     * @return 添加权限视图
     */
    @GetMapping("/add")
    public ModelAndView add(ModelAndView view){
        view.setViewName("/role/add");
        return view;
    }

    /**
     *
     * @param id 编辑id
     * @param view 视图
     * @return 编辑权限视图
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Integer id,ModelAndView view ){
        Role role = roleDao.getOne(id);
        view.addObject("role" , role);
        view.setViewName("/role/edit");
        return view;
    }

    /**
     *
     * @param role  权限实体
     * @param bindingResult 错误信息
     * @return 添加权限
     */
    @ResponseBody
    @PostMapping("/add")
    public Result add(@Valid Role role , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtils.error(1002, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        role.setName(role.getName());
        role.setAvailable(true);
        role.setRoles(role.getRoles());
        roleDao.save(role);
        return ResultUtils.success(role);
    }

    /**
     *
     * @param role 权限实体
     * @param bindingResult 错误信息
     * @return 编辑权限
     */
    @ResponseBody
    @PostMapping("/edit")
    public Result edit( @Valid Role role , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtils.error(1002, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        role.setId(role.getId());
        role.setName(role.getName());
        role.setAvailable(role.getAvailable());
        role.setRoles(role.getRoles());
        roleDao.save(role);
        return ResultUtils.success(role);
    }

    /**
     *
     * @return 获取所有权限角色
     */
    @ResponseBody
    @PostMapping("/list")
    public Object list(@PageableDefault(size = 5)Pageable pageable , Role role){
        pageable = PageRequest.of(pageable.getPageNumber() - 1 , pageable.getPageSize());
        Page<Role> list = roleService.findAll(pageable , role);
        return ResultUtils.layPage(list.getTotalElements() , list.getContent());
    }

    /**
     *
     * @param id 权限角色id
     * @return 删除权限角色
     */
    @ResponseBody
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){

        roleDao.deleteById(id);
        return ResultUtils.success(id);
    }

}
