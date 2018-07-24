package com.way.fact.mongodb;

import com.way.fact.mongo.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final Logger log = LoggerFactory.getLogger(MongoTest.class);

    /**
     * 文章详情
     */
    @Test
    public void find(){

        Article article = mongoTemplate.findOne(new Query(Criteria.where("_id").is("5b5138031e5d7c0b6c1af7ee")) , Article.class);

        assert article != null;
        System.out.println(article.getTitle());
    }

    /**
     * 文章列表
     */
    @Test
    public void findAll(){
        List<Article> article = mongoTemplate.findAll(Article.class);
        System.out.println(article);
        for (Article art : article){
            log.info("_id = {} , title = {} , by = {}" , art.get_id() , art.getTitle() , art.getBy());
        }

    }

    /**
     * 添加文章
     */
    @Test
    public void add(){
        Article article = new Article();
        article.setTitle("fact");
        article.setBy("root");
        mongoTemplate.save(article);
    }

    /**
     * 修改文章
     */
    @Test
    public void edit(){
        Article article = new Article();
        article.set_id("5b5138031e5d7c0b6c1af7ee");
        article.setTitle("fact");
        article.setBy("root");
        mongoTemplate.save(article);
    }

    /**
     * 删除文章
     */
    @Test
    public void delete(){
        mongoTemplate.remove(new Query(Criteria.where("_id").is("5b5138031e5d7c0b6c1af7ee")) , Article.class);
    }

}
