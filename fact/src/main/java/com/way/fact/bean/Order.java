package com.way.fact.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 订单实体
 * @author yrz
 */
@Entity
public class Order {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 购买商品信息
     */
    private String goodsName;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 商品总额
     */
    private String sum;

    /**
     * 商品总数
     */
    private Integer num;

    /**
     * 买家
     */
    private Integer uid;

    /**
     * 订单创建时间
     */
    private Date createTime;

    /**
     * 订单修改时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
