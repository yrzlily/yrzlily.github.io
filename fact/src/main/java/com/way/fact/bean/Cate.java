package com.way.fact.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * 分类实体
 * @author yrz
 */
@Entity

public class Cate {

    /**
     * ID
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 分类名称
     */
    @NotBlank
    private String name;

    /**
     * 上级分类
     */
    private Integer parentId;

    /**
     * 分类排序
     */
    private Integer sort;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
