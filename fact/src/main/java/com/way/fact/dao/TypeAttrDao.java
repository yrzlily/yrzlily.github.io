package com.way.fact.dao;

import com.way.fact.bean.type.TypeAttr;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 分类规格持久层
 * @author yrz
 */
@Repository
public interface TypeAttrDao extends JpaRepository<TypeAttr, Integer> {

    /**
     * 寻找对应的分类规格属性
     * @param specification
     * @param pageable
     * @return
     */
    Page<TypeAttr> findAll(Specification<TypeAttr> specification ,  Pageable pageable);

}
