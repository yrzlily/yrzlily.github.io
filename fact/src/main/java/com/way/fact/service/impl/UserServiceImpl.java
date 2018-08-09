package com.way.fact.service.impl;

import com.way.fact.bean.User;
import com.way.fact.dao.UserDao;
import com.way.fact.service.UserService;
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
 * @author yrz
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 用户列表分页模糊查询
     * @param pageable
     * @param username
     * @return
     */
    @Override
    public Page<User> findAll(
            Pageable pageable,
            String username
            ){
        return userDao.findAll(new Specification<User>() {

            @Override
            public Specification<User> or(Specification<User> other) {
                return null;
            }

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                if(username != null){
                    criteriaQuery.where(criteriaBuilder.like(root.get("username").as(String.class) ,"%" + username + "%"));
                }

                return criteriaQuery.getRestriction();
            }
        } , pageable);
    }

}
