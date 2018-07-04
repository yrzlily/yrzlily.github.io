package com.way.fact.bean;

import javax.persistence.*;
import java.util.List;

/**
 * @author Administrator
 */
@Entity
@Table(name = "articleInfo")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    private String info;

    @ManyToMany(mappedBy = "books")
    private List<Article> article;

    public List<Article> getArticle() {
        return article;
    }

    public void setArticle(List<Article> article) {
        this.article = article;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
