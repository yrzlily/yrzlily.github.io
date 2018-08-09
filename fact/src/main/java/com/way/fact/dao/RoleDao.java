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
     * @param pageable
     * @param role
     * @return
     */
    Page<Role> findAll(Specification<Role> pageable , Pageable role);

}
