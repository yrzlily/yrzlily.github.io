package com.way.fact.mongodao;

import com.way.fact.mongo.Article;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * 文章持久层
 * @author yrz
 */
public interface ArticleDao {

    /**
     * 获取所有文章
     * @return 文章列表
     */
    List<Article> list();

    /**
     * 计算总数
     * @param query 条件
     * @return 总数
     */
    Long count(Query query);

    /**
     * 添加文章
     * @param article 文章实体
     */
    void add(Article article);

    /**
     * 删除文章
     * @param id 文章id
     */
    void delete(String id);

    /**
     * 文章详情
     * @param id 文章id
     * @return 详情
     */
    Article find(String id);

    /**
     * 编辑文章
     * @param article 文章实体
     */
    void edit(Article article);
}
