package com.way.fact.dao;

import com.way.fact.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yrz
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    /**
     * 通过用户名寻找
     * @param username 用户名称
     * @return 用户信息
     */
    User findByUsername(String username);

    /**
     * 用户列表
     * @param specification 查询条件
     * @param pageable 分页信息
     * @return 用户结果
     */
    Page<User> findAll(Specification<User> specification, Pageable pageable);

}
