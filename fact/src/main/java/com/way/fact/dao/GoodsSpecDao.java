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
     * @param gid 商品id
     * @return 匹配规格
     */
    List<GoodsSpec> findAllByGid(Integer gid);

    /**
     * 修改库存
     * @param id 规格id
     * @param num 库存
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update GoodsSpec gs set gs.num = ?2 where gs.id = ?1")
    void updateNum(Integer id , Integer num);
}
