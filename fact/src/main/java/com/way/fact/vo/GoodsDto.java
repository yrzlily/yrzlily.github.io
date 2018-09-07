package com.way.fact.vo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * goodsDto
 * @author yrz
 */
public class GoodsDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品id
     */
    private Integer id;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 封面图
     */
    private String images;

    /**
     * 产品排序
     */
    private Integer sort;

    /**
     * 产品描述
     */
    private String Description;

    /**
     * 修改时间
     */
    private Timestamp update_time;

    /**
     * 库存
     */
    private Integer num;

    /**
     * 产品状态
     * 0=上架
     * 1=下架
     */
    private String status;

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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GoodsDto['id'="+this.id+"]";
    }
}
