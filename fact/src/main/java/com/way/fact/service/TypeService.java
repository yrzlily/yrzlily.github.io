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
     * @param pageable 分页信息
     * @param search 搜索条件
     * @param pid 上级id
     * @return 当前分类列表
     */
    Page<Type> findAll(Pageable pageable , String search , Integer pid);


    /**
     * 父类递归
     * @param id 子节点
     * @return 父节点
     */
    Type parent(Integer id);

    /**
     * 子类递归
     * @param list 所有分类
     * @param parentID 父节点
     * @return 子节点
     */
    List<Type> child(List<Type> list , Integer parentID);



}
