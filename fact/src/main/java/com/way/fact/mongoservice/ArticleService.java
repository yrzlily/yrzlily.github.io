package com.way.fact.mongoservice;

import com.way.fact.mongo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 文章服务层接口
 * @author yrz
 */
public interface ArticleService {

    /**
     * 分页查询
     * @param pageable 分页
     * @param search 搜索
     * @param cate 分类
     * @return 分页条数
     */
    Page<Article> list(Pageable pageable, String search , Integer cate);

}
