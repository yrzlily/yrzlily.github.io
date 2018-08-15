package com.way.fact.mongodao.impl;

import com.way.fact.mongo.Article;
import com.way.fact.mongodao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *文章持久层实现类
 * @author yrz
 */
@Repository
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     *
     * @return 获取所有文章
     */
    @Override
    public List<Article> list() {
        return mongoTemplate.findAll(Article.class);
    }

    /**
     * 计算文章总数
     * @param query 条件
     * @return 总数
     */
    @Override
    public Long count(Query query) {
        return mongoTemplate.count(query , Article.class);
    }

    /**
     *
     * @param article 添加文章
     */
    @Override
    public void add(Article article) {
        mongoTemplate.save(article);
    }

    /**
     * 删除文章
     * @param id 删除id
     */
    @Override
    public void delete(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query , Article.class);
    }

    /**
     * 文章详情
     * @param id 文章id
     * @return 详情
     */
    @Override
    public Article find(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query , Article.class);
    }

    /**
     * 编辑文章
     * @param article 文章实体
     */
    @Override
    public void edit(Article article) {
        Query query = new Query(Criteria.where("_id").is(article.getId()));
        Update update = Update.update("title" , article.getTitle()).set("by" , article.getBy()).set("description" , article.getDescription()).set("content" , article.getContent()).set("images" , article.getImages()).set("cate" , article.getCate()).set("timestamp" , article.getTimestamp());
        mongoTemplate.updateFirst(query ,update  ,Article.class);
    }
}
