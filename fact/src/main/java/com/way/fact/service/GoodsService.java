package com.way.fact.service;

import com.way.fact.bean.Goods;
import com.way.fact.dao.GoodsDao;
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
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    /**
     * 分页模糊查询
     * @param pageable
     * @param goods
     * @return
     */
    public Page<Goods> findAll(
            Pageable pageable,
            Goods goods
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
                if(goods.getName()!=null){
                    predicate.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+goods.getName()+"%"));
                }
                Predicate[] predicates = new Predicate[predicate.size()];
                criteriaQuery.where(criteriaBuilder.and(predicate.toArray(predicates)));
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id").as(Integer.class)));
                criteriaQuery.distinct(true);
                return criteriaQuery.getRestriction();
            }
        },pageable);
    }




}
