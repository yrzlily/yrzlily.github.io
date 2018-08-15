package com.way.fact.service;

import com.way.fact.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 用户服务层
 * @author yrz
 */
public interface UserService {

    /**
     * 分页查找
     * @param pageable 分页信息
     * @param username 查询条件
     * @return 搜索结果
     */
    Page<User> findAll(Pageable pageable, String username);

}
