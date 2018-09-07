package com.fact.task.bean;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * order实体
 * @author yrz
 */
@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 商品订单
     */
    @Column(name = "order_id")
    @SerializedName(value = "orderId")
    private String orderId;

    /**
     * 订单商品信息
     */
    @Column(name = "goods_message")
    @SerializedName(value = "goodsMessage")
    private String goodsMessage;

    /**
     * 订单状态
     */
    private Enum status;

    /**
     * 总价格
     */
    private BigDecimal sum;

    /**
     * 购买用户
     */
    private Integer uid;

    @Column(name = "create_time")
    @SerializedName(value = "createTime")
    private Timestamp createTime;

    @Column(name ="update_time")
    @SerializedName(value = "updateTime")
    private Timestamp updateTime;
}
