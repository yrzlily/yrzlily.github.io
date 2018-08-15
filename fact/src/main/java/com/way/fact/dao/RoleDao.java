package com.way.fact.dao;

import com.way.fact.bean.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yrz
 */
@Repository
public interface RoleDao extends JpaRepository<Role , Integer> {

    /**
     * 分页查询
     * @param pageable   分页信息
     * @param role  查询条件
     * @return
     */
    Page<Role> findAll(Specification<Role> role , Pageable pageable);

}
