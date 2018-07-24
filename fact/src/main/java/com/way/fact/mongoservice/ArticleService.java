package com.way.fact.mongoservice;

import com.way.fact.mongo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 文章服务层接口
 * @author Administrator
 */
public interface ArticleService {

    /**
     * 分页查询
     * @param pageable
     * @param filter
     * @return
     */
    Page<Article> list(Pageable pageable, String filter);

}
