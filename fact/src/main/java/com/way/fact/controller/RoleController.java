package com.way.fact.controller;

import com.way.fact.bean.Result;
import com.way.fact.bean.Role;
import com.way.fact.dao.RoleDao;
import com.way.fact.service.RoleService;
import com.way.fact.utils.ResultUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 管理权限控制层
 * @author Administrator
 */
@RequiresRoles("admin")
@RequestMapping(value = "/role" , method = RequestMethod.GET)
@RestController
public class RoleController {

    private RoleDao roleDao;
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    /**
     * 获取所有权限角色
     * @return
     */
    @RequestMapping("/index")
    public Result index(@PageableDefault(size = 5)Pageable pageable , @Valid Role role){
        Object list = roleService.findAll(pageable , role);
        return ResultUtils.success(list);
    }



}
