package com.way.fact.service;

import com.way.fact.bean.Nav;

import java.util.List;
import java.util.Optional;

/**
 * 导航服务层
 * @author Administrator
 */
public interface NavService {

    /**
     * 添加导航
     * @param nav
     */
    void addNav(Nav nav);

    /**
     * 编辑导航
     * @param nav
     */
    void editNav(Nav nav);

    /**
     * 删除导航
     * @param id
     * @return
     */
    Optional<Nav> delNav(Integer id);

    /**
     * 寻找所有导航
     * @return
     */
    List<Nav> findALL();

    /**
     * 导航详情
     * @param id
     * @return
     */
    Optional<Nav> findOne(Integer id);

    /**
     * 递归父节点寻找子节点
     * @param nav
     * @param parentId
     * @return
     */
    List<Nav> findAllByParentId(List<Nav> nav , Integer parentId);

    /**
     * 递归寻找父节点
     * @param nav
     * @return
     * @throws Exception
     */
    Nav findAllByChild(Nav nav) ;
}
