package com.way.fact.mongo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 *文章实体
 * mongo
 * @author Administrator
 */
@Data
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
}
