package com.way.fact.service;

import com.way.fact.bean.type.TypeAttr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

}
