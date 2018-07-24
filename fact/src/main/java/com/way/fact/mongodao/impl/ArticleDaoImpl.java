package com.way.fact.mongodao.impl;

import com.way.fact.mongo.Article;
import com.way.fact.mongodao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *文章持久层实现类
 * @author Administrator
 */
@Repository
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 获取所有文章
     * @return
     */
    @Override
    public List<Article> list() {
        return mongoTemplate.findAll(Article.class);
    }

    /**
     * 计算文章总数
     * @param query
     * @return
     */
    @Override
    public Long count(Query query) {
        return mongoTemplate.count(query , Article.class);
    }

    /**
     * 添加文章
     * @param article
     */
    @Override
    public void add(Article article) {
        mongoTemplate.save(article);
    }

    /**
     * 删除文章
     * @param id
     */
    @Override
    public void delete(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query , Article.class);
    }
}
