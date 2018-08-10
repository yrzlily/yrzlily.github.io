package com.way.fact.service;

import com.way.fact.bean.goods.GoodsAttr;
import com.way.fact.bean.type.Type;
import com.way.fact.bean.type.TypeAttr;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 分类服务层接口
 * @author yrz
 */
public interface TypeService {

    /**
     * 分页查询
     * @param pageable
     * @param search
     * @param pid
     * @return
     */
    Page<Type> findAll(Pageable pageable , String search , Integer pid);


    /**
     * 父类递归
     * @param id
     * @return
     */
    Type parent(Integer id);

    /**
     * 子类递归
     * @param list
     * @param parentID
     * @return
     */
    List<Type> child(List<Type> list , Integer parentID);



}
