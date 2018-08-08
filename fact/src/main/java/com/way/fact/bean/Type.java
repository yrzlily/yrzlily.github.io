package com.way.fact.bean;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类实体
 * @author Administrator
 */
@Entity
public class Type {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 分类名称
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 0=顶级
     * 上级分类
     */
    @Column(name = "parent_id")
    private Integer parentID;

    /**
     * 分类图标
     */
    private String Images;

    /**
     * 分类排序
     */
    private Integer sort;

    /**
     * 更新时间
     */
    private Timestamp update_time;

    /**
     * 递归
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @Transient
    private Type types;

//    @JsonIgnoreProperties("typeList")
//    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "typeList")
//    private List<Goods> goodsList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public Type getTypes() {
        return types;
    }

    public void setTypes(Type types) {
        this.types = types;
    }
}
