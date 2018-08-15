package com.way.fact.service;

import com.way.fact.bean.goods.GoodsAttr;
import com.way.fact.bean.type.TypeAttr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author yrz
 */
public interface TypeAttrService {

    /**
     * 属性分页查询
     * @param pageable 分页信息
     * @param tid 分类id
     * @return 分类信息
     */
    Page<TypeAttr> findAll(Pageable pageable , Integer tid);

    /**
     * 寻找相关产品对应规格
     * @param typeAttrList 规格属性列表
     * @param goodsAttrList 商品属性列表
     * @return 搜索结果
     */
    List<TypeAttr> findSpecifications(List<TypeAttr> typeAttrList , List<GoodsAttr> goodsAttrList);
}
