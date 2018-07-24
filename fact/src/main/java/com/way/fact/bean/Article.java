package com.way.fact.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author Administrator
 */
@Data
@Entity
public class Article {

    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    private String images;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "article_book", joinColumns = {@JoinColumn(name = "article_id")},inverseJoinColumns = {@JoinColumn(name = "book_id")})
    private List<Book> books;
}
