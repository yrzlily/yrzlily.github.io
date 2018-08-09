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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 文章服务层实现类
 * @author yrz
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
     * @param search
     * @return
     */
    @Override
    public Page<Article> list(Pageable pageable, String search , Integer cate) {
        Query query = new Query();

        //类型搜索
        if(cate != null){
            query.addCriteria(Criteria.where("cate").is(cate));
        }

        //标题模糊搜索
        if(search != null){
            query.addCriteria(Criteria.where("title").regex(".*?\\" + search + ".*"));
        }

        query = query.with(pageable).with(new Sort(Sort.Direction.DESC , "timestamp"));

        //数据总数
        Long count = articleDao.count(query);
        //查询全部
        List<Article> list = mongoTemplate.find(query , Article.class);

        for (Article article: list){
            article.setTimestamp(article.getTimestamp());
        }

        return new PageImpl<>(list , pageable , count);
    }
}
