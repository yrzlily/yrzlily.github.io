package com.way.fact.service;

import com.way.fact.bean.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 权限服务层
 * @author yrz
 */
public interface RoleService {

    Page<Role> findAll(Pageable pageable , Role role);

}
