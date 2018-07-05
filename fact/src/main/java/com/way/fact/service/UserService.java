package com.way.fact.service;

import com.way.fact.bean.User;
import com.way.fact.dao.UserDao;
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
 * @author Administrator
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 用户列表分页模糊查询
     * @param pageable
     * @param user
     * @return
     */
    public Page<User> findAll(
            Pageable pageable,
            User user
            ){
        return userDao.findAll(new Specification<User>() {

            @Override
            public Specification<User> or(Specification<User> other) {
                return null;
            }

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                if(user.getUsername() != null){
                    criteriaQuery.where(criteriaBuilder.like(root.get("username").as(String.class) ,"%" + user.getUsername() + "%"));
                }

                return criteriaQuery.getRestriction();
            }
        } , pageable);
    }

}
