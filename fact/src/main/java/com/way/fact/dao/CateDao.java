package com.way.fact.dao;

import com.way.fact.bean.Cate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 分类持久层
 * @author Administrator
 */
@Repository
public interface CateDao extends JpaRepository<Cate , Integer> {

    /**
     * 分页查找
     * @param specification
     * @param pageable
     * @return
     */
    Page<Cate> findAll(Specification<Cate> specification, Pageable pageable);

}
