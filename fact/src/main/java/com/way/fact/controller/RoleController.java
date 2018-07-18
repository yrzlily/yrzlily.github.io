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

/**
 * 管理权限控制层
 * @author Administrator
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
     * 权限列表视图
     * @param view
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(ModelAndView view){
        view.setViewName("/role/index");
        return view;
    }

    /**
     * 添加权限视图
     * @param view
     * @return
     */
    @GetMapping("/add")
    public ModelAndView add(ModelAndView view){
        view.setViewName("/role/add");
        return view;
    }

    /**
     * 编辑权限视图
     * @param id
     * @param view
     * @return
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Integer id,ModelAndView view ){
        Role role = roleDao.getOne(id);
        view.addObject("role" , role);
        view.setViewName("/role/edit");
        return view;
    }

    /**
     * 添加权限
     * @param role
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @PostMapping("/add")
    public Result add(@Valid Role role , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtils.error(1002,bindingResult.getFieldError().getDefaultMessage());
        }

        role.setName(role.getName());
        role.setAvailable(true);
        role.setRoles(role.getRoles());
        roleDao.save(role);
        return ResultUtils.success(role);
    }

    /**
     * 编辑权限
     * @param role
     * @param bindingResult
     * @return
     */
    @ResponseBody
    @PostMapping("/edit")
    public Result edit( @Valid Role role , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtils.error(1002,bindingResult.getFieldError().getDefaultMessage());
        }

        role.setId(role.getId());
        role.setName(role.getName());
        role.setAvailable(role.getAvailable());
        role.setRoles(role.getRoles());
        roleDao.save(role);
        return ResultUtils.success(role);
    }

    /**
     * 获取所有权限角色
     * @return
     */
    @ResponseBody
    @PostMapping("/list")
    public Object list(@PageableDefault(size = 5)Pageable pageable , Role role){
        pageable = PageRequest.of(pageable.getPageNumber() - 1 , pageable.getPageSize());
        Page<Role> list = roleService.findAll(pageable , role);
        return ResultUtils.layPage(list.getTotalPages() , list.getContent());
    }

    /**
     * 删除权限角色
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){

        roleDao.deleteById(id);
        return ResultUtils.success(id);
    }

}
