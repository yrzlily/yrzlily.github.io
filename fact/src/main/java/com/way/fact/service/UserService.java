package com.way.fact.service;

import com.way.fact.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 用户服务层
 * @author Administrator
 */
public interface UserService {

    Page<User> findAll(Pageable pageable, String username);

}
