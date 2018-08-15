package com.way.fact.mongo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *文章实体
 * mongo
 * @author yrz
 */
public class Article implements Serializable {

    private static final long serialVersionUID  = -3258839839160856613L;

    /**
     * ID
     */
    @Id
    @Column(name = "_id")
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 文章分类
     */
    private Integer cate;

    /**
     * 作者
     */
    private String by;

    /**
     * 封面
     */
    private String images;

    /**
     * 简介
     */
    private String description;

    /**
     * 详情
     */
    private String content;

    /**
     * 更新时间
     */
    private Object timestamp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCate() {
        return cate;
    }

    public void setCate(Integer cate) {
        this.cate = cate;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Object getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Object timestamp) {
        this.timestamp = timestamp;
    }
}
