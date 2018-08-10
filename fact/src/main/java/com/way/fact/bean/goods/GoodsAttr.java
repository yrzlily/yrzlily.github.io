package com.way.fact.bean.goods;

import javax.persistence.*;
import java.util.List;

/**
 * 规格属性实体
 * @author yrz
 */
@Entity
public class GoodsAttr {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 对应类型
     */
    private Integer goodsAttrTid;

    /**
     * 规格名称
     */
    @Column(name = "goods_attr_name")
    private String goodsAttrName;

    /**
     * 规格价格
     */
    @Column(name = "goods_attr_price")
    private String goodsAttrPrice;

    /**
     * 对应商品
     */
    private Integer goodsAttrGid;

    /**
     * 表单接受参数
     */
    @Transient
    private List<GoodsAttr> goodsAttrs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoodsAttrTid() {
        return goodsAttrTid;
    }

    public void setGoodsAttrTid(Integer goodsAttrTid) {
        this.goodsAttrTid = goodsAttrTid;
    }

    public String getGoodsAttrName() {
        return goodsAttrName;
    }

    public void setGoodsAttrName(String goodsAttrName) {
        this.goodsAttrName = goodsAttrName;
    }

    public String getGoodsAttrPrice() {
        return goodsAttrPrice;
    }

    public void setGoodsAttrPrice(String goodsAttrPrice) {
        this.goodsAttrPrice = goodsAttrPrice;
    }

    public List<GoodsAttr> getGoodsAttrs() {
        return goodsAttrs;
    }

    public void setGoodsAttrs(List<GoodsAttr> goodsAttrs) {
        this.goodsAttrs = goodsAttrs;
    }

    public Integer getGoodsAttrGid() {
        return goodsAttrGid;
    }

    public void setGoodsAttrGid(Integer goodsAttrGid) {
        this.goodsAttrGid = goodsAttrGid;
    }
}
