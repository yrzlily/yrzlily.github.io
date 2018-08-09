package com.way.fact.service.impl;

import com.way.fact.bean.Cate;
import com.way.fact.dao.CateDao;
import com.way.fact.service.CateService;
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
 * 分类服务实现层
 * @author yrz
 */
@Service
public class CateServiceImpl implements CateService {

    @Autowired
    private CateDao cateDao;

    /**
     * 分类分页查询
     * @param pageable
     * @param cate
     * @return
     */
    @Override
    public Page<Cate> findAll(Pageable pageable, Cate cate) {
        return cateDao.findAll(new Specification<Cate>() {

            @Override
            public Specification<Cate> or(Specification<Cate> other) {
                return null;
            }

            @Override
            public Predicate toPredicate(Root<Cate> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                if(cate.getName() != null){
                    criteriaQuery.where(criteriaBuilder.like(root.get("name").as(String.class) ,"%" + cate.getName() + "%"));

                }
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("sort").as(Integer.class)));
                return criteriaQuery.getRestriction();
            }
        },pageable);
    }
}
