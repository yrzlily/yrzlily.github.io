package com.way.fact.service.impl;

import com.way.fact.bean.Role;
import com.way.fact.dao.RoleDao;
import com.way.fact.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * 权限管理服务层
 * @author Administrator
 */
@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    /**
     * 分页查询所有
     * @param pageable
     * @param role
     * @return
     */
    @Override
    public Page<Role> findAll(Pageable pageable , Role role){
        return roleDao.findAll(new Specification<Role>() {
            @Override
            public Specification<Role> and(Specification<Role> other) {
                return null;
            }

            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        }, pageable);
    }

}
