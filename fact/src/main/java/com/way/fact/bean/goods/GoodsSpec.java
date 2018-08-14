package com.way.fact.bean.goods;

import javax.persistence.*;
import java.util.List;

/**
 * 规格匹配实体
 * @author yrz
 */
@Entity
public class GoodsSpec {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 对应商品
     */
    private Integer gid;

    /**
     * 规格匹配
     */
    private String spec;

    /**
     * 规格库存
     */
    private Integer num;

    @Transient
    private List<Integer> list;

    @Transient
    private List<GoodsAttr> goodsAttrs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public List<GoodsAttr> getGoodsAttrs() {
        return goodsAttrs;
    }

    public void setGoodsAttrs(List<GoodsAttr> goodsAttrs) {
        this.goodsAttrs = goodsAttrs;
    }
}
