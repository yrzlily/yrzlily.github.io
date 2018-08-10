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
     * @param pageable
     * @param tid
     * @return
     */
    Page<TypeAttr> findAll(Pageable pageable , Integer tid);

    /**
     * 寻找相关产品对应规格
     * @param typeAttrList
     * @param goodsAttrList
     * @return
     */
    List<TypeAttr> findSpecifications(List<TypeAttr> typeAttrList , List<GoodsAttr> goodsAttrList);
}
