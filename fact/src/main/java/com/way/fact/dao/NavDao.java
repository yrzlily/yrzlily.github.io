package com.way.fact.dao;

import com.way.fact.bean.Nav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author yrz
 */
@Repository
public interface NavDao extends JpaRepository<Nav,Integer> {

    /**
     * 通过父栏目寻找
     * @param parentId 父节点id
     * @return 子节点
     */
    List<Nav> findAllByParentId(Integer parentId);

    /**
     * 模糊查询
     * @param str 查询条件
     * @return 结果集
     */
    List<Nav> findByNameLike(String str);
}
