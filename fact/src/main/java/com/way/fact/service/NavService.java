package com.way.fact.service;

import com.way.fact.bean.Nav;

import java.util.List;
import java.util.Optional;

/**
 * 导航服务层
 * @author Administrator
 */
public interface NavService {

    void addNav(Nav nav);

    void editNav(Nav nav);

    Optional<Nav> delNav(Integer id);

    List<Nav> findALL();

    Optional<Nav> findOne(Integer id);

    List<Nav> findAllByParentId(List<Nav> nav , Integer parentId);

}
