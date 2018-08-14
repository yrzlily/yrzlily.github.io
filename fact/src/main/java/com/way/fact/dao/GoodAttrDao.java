package com.way.fact.dao;

import com.way.fact.bean.goods.GoodsAttr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品规格持久层
 * @author yrz
 */
@Repository
public interface GoodAttrDao extends JpaRepository<GoodsAttr , Integer> {

    /**
     * 通过商品寻找规格
     * @param gid
     * @return
     */
    List<GoodsAttr> findByGoodsAttrGid(Integer gid);

    /**
     * 通过集合查询
     * @param id
     * @return
     */
    @Query("select ga from GoodsAttr ga where ga.id in ?1")
    List<GoodsAttr> findAllByList(List<Integer> id);
}
