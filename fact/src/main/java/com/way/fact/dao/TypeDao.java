package com.way.fact.dao;

import com.way.fact.bean.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 商品分类持久层
 * @author Administrator
 */
@Repository
public interface TypeDao extends JpaRepository<Type,Integer> {

    /**
     * 分页条件查询
     * @param specification
     * @param pageable
     * @return
     */
    Page<Type> findAll(Specification<Type> specification, Pageable pageable);

    /**
     * 过滤字段
     * @return
     */
    @Query("select tp.id , tp.typeName , tp.parentID from Type tp")
    List<Object> fieldAll();

    /**
     * 统计下级分类
     * @param id
     * @return
     */
    Integer countByParentID(Integer id);
}
