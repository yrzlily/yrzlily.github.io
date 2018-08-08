package com.way.fact.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * 商品详情
 * @author Administrator
 */
@Entity
public class GoodsContent {

    @Id
    @GeneratedValue
    private Integer id;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
