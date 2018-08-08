package com.way.fact.dao;

import com.way.fact.bean.GoodsContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * 商品详情
 * @author Administrator
 */
@Repository
public interface GoodsContentDao extends JpaRepository<GoodsContent , Integer> {
}
