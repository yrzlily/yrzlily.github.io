package com.way.fact.mongoservice.impl;

import com.way.fact.mongo.Article;
import com.way.fact.mongodao.ArticleDao;
import com.way.fact.mongoservice.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 文章服务层实现类
 * @author Administrator
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 文章分页查询
     * @param pageable
     * @param filter
     * @return
     */
    @Override
    public Page<Article> list(Pageable pageable, String filter) {
        Query query = new Query();
        query = query.with(pageable).with(new Sort(Sort.Direction.DESC , "timestamp"));

        Long count = articleDao.count(query);
        List<Article> list = mongoTemplate.find(query , Article.class);

        for (Article article: list){
            article.setTimestamp(article.getTimestamp());
        }

        return new PageImpl<>(list , pageable , count);
    }
}
