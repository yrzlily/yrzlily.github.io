package com.way.fact.dao;

import com.way.fact.bean.goods.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yrz
 */
@Repository
public interface GoodsDao extends JpaRepository<Goods ,Integer> {
    /**
     * 多条件分页查询
     * @param specification 查询条件
     * @param pageable 分页信息
     * @return 查询列表
     */
    Page<Goods> findAll(Specification<Goods> specification, Pageable pageable);

}
