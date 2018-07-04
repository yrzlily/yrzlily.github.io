package com.way.fact.dao;

import com.way.fact.bean.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 */
@Repository
public interface GoodsDao extends JpaRepository<Goods ,Integer> {
    /**
     * 多条件分页查询
     * @param specification
     * @param pageable
     * @return
     */
    Page<Goods> findAll(Specification<Goods> specification, Pageable pageable);

}
