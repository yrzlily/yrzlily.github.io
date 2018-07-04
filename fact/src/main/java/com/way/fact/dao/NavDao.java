package com.way.fact.dao;

import com.way.fact.bean.Nav;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author Administrator
 */
@Repository
public interface NavDao extends JpaRepository<Nav,Integer> {

    /**
     * 通过父栏目寻找
     * @param parentId
     * @return
     */
    List<Nav> findAllByParentId(Integer parentId);

    /**
     * 模糊查询
     * @param str
     * @return
     */
    List<Nav> findByNameLike(String str);
}
