package com.way.fact.mongodao;

import com.way.fact.mongo.Article;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * 文章持久层
 * @author Administrator
 */
public interface ArticleDao {

    /**
     * 获取所有文章
     * @return
     */
    List<Article> list();

    /**
     * 计算总数
     * @param query
     * @return
     */
    Long count(Query query);

    /**
     * 添加文章
     * @param article
     */
    void add(Article article);

    /**
     * 删除文章
     * @param id
     */
    void delete(String id);

    /**
     * 文章详情
     * @param id
     * @return
     */
    Article find(String id);

    /**
     * 编辑文章
     * @param article
     */
    void edit(Article article);
}
