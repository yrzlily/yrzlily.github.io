package com.way.fact.service;


import com.way.fact.bean.Cate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 分类服务层
 * @author yrz
 */
public interface CateService {

    Page<Cate> findAll(Pageable pageable , Cate cate);

}
