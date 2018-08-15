package com.way.fact.service;


import com.way.fact.bean.Cate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 分类服务层
 * @author yrz
 */
public interface CateService {

    /**
     * 分页查询
     * @param pageable 分页信息
     * @param cate 分类实体
     * @return 分页列表
     */
    Page<Cate> findAll(Pageable pageable , Cate cate);

}
