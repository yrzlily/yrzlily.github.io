package com.way.fact.dao;

import com.way.fact.bean.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Administrator
 */
@Repository
public interface ArticleDao extends JpaRepository<Article,Integer> ,JpaSpecificationExecutor<Article> {


}
