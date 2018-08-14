package com.way.fact.dao;

import com.way.fact.bean.goods.GoodsSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 规格匹配持久层
 * @author yrz
 */
@Repository
public interface GoodsSpecDao extends JpaRepository<GoodsSpec , Integer> {

    /**
     * 通过商品寻找
     * @param gid
     * @return
     */
    List<GoodsSpec> findAllByGid(Integer gid);

    /**
     * 修改库存
     * @param id
     * @param num
     */
    @Transactional
    @Modifying
    @Query("update GoodsSpec gs set gs.num = ?2 where gs.id = ?1")
    void updateNum(Integer id , Integer num);
}
