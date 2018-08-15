package com.way.fact.service;

import com.way.fact.bean.goods.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 商品服务层
 * @author yrz
 */
public interface GoodsService {

    /**
     * 分页查找
     * @param pageable 分页信息
     * @param name 查询条件
     * @return 分页结果
     */
    Page<Goods> findAll(Pageable pageable, String name);

}
