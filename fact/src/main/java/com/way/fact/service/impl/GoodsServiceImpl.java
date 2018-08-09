package com.way.fact.service.impl;

import com.way.fact.bean.goods.Goods;
import com.way.fact.dao.GoodsDao;
import com.way.fact.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yrz
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    /**
     * 分页模糊查询
     * @param pageable
     * @param search
     * @return
     */
    @Override
    public Page<Goods> findAll(
            Pageable pageable,
            String search
    ){

        return goodsDao.findAll(new Specification<Goods>(){

            @Override
            public Specification<Goods> or(Specification<Goods> other) {
                return null;
            }

            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicate = new ArrayList<>();
                //名称模糊查询
                if(search != null){
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+ search +"%"));
                }
                Predicate[] predicates = new Predicate[predicate.size()];
                criteriaQuery.where(criteriaBuilder.and(predicate.toArray(predicates)));
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("sort").as(Integer.class)));
                criteriaQuery.distinct(true);
                return criteriaQuery.getRestriction();
            }
        },pageable);
    }




}
