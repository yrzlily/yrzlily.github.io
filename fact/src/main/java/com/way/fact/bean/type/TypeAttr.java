package com.way.fact.bean.type;

import com.way.fact.bean.goods.GoodsAttr;

import javax.persistence.*;
import java.util.List;

/**
 * 商品规格属性
 * @author yrz
 */
@Entity
public class TypeAttr {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 对应分类
     */
    private Integer tid;

    /**
     * 分类规格名称
     */
    @Column(name = "type_attributes_name")
    private String typeAttributesName;

    /**
     * 分类规格属性
     */
    @Column(name = "type_attributes_type")
    private String typeAttributesType;

    /**
     * 分类规格排序
     */
    private Integer sort;

    @Transient
    private List<GoodsAttr> goodsAttrList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTypeAttributesName() {
        return typeAttributesName;
    }

    public void setTypeAttributesName(String typeAttributesName) {
        this.typeAttributesName = typeAttributesName;
    }

    public String getTypeAttributesType() {
        return typeAttributesType;
    }

    public void setTypeAttributesType(String typeAttributesType) {
        this.typeAttributesType = typeAttributesType;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<GoodsAttr> getGoodsAttrList() {
        return goodsAttrList;
    }

    public void setGoodsAttrList(List<GoodsAttr> goodsAttrList) {
        this.goodsAttrList = goodsAttrList;
    }
}
