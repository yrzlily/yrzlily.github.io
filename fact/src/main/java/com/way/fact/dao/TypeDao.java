package com.way.fact.dao;

import com.way.fact.bean.type.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品分类持久层
 * @author yrz
 */
@Repository
public interface TypeDao extends JpaRepository<Type,Integer> {

    /**
     * 分页条件查询
     * @param specification 查询条件
     * @param pageable 分页信息
     * @return 结果集
     */
    Page<Type> findAll(Specification<Type> specification, Pageable pageable);

    /**
     * 过滤字段
     * @return 过滤字段
     */
    @Query("select tp.id , tp.typeName , tp.parentID from Type tp")
    List<Object> fieldAll();

    /**
     * 寻找子节点
     * @param parentId 父节点
     * @return 子节点
     */
    List<Type> findByParentID(Integer parentId);

    /**
     * 统计下级分类
     * @param id 父节点id
     * @return 下级分类列表
     */
    Integer countByParentID(Integer id);


    /**
     * 过滤寻找
     * @param cid 分类id
     * @return 分类信息
     */
    @Query("select tp.id , ta from Type tp join tp.typeAttrs ta where tp.id=?1 ")
    List<Object[]> findAttr(Integer cid);
}
