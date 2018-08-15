package com.way.fact.service;

import com.way.fact.bean.Nav;

import java.util.List;
import java.util.Optional;

/**
 * 导航服务层
 * @author yrz
 */
public interface NavService {

    /**
     * 添加导航
     * @param nav 导航实体
     */
    void addNav(Nav nav);

    /**
     * 编辑导航
     * @param nav 导航实体
     */
    void editNav(Nav nav);

    /**
     * 删除导航
     * @param id 导航id
     * @return 删除导航
     */
    Optional<Nav> delNav(Integer id);

    /**
     * 寻找所有导航
     * @return 寻找所有导航
     */
    List<Nav> findALL();

    /**
     * 导航详情
     * @param id 导航id
     * @return 导航详情
     */
    Optional<Nav> findOne(Integer id);

    /**
     * 递归父节点寻找子节点
     * @param nav 导航实体
     * @param parentId 父节点
     * @return 子节点
     */
    List<Nav> findAllByParentId(List<Nav> nav , Integer parentId);

    /**
     * 递归寻找父节点
     * @param nav 导航实体
     * @return 子节点
     */
    Nav findAllByChild(Nav nav) ;
}
