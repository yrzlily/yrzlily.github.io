package com.way.fact.dao;

import com.way.fact.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    /**
     * 通过用户名寻找
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 用户列表
     * @param specification
     * @param pageable
     * @return
     */
    Page<User> findAll(Specification<User> specification, Pageable pageable);

}
