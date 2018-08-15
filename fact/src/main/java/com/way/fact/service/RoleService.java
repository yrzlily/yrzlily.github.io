package com.way.fact.service;

import com.way.fact.bean.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 权限服务层
 * @author yrz
 */
public interface RoleService {

    /**
     * 角色分页
     * @param pageable 分页信息
     * @param role  角色实体
     * @return 角色列表
     */
    Page<Role> findAll(Pageable pageable , Role role);

}
