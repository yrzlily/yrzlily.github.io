package com.way.fact.bean;

import lombok.Data;

/**
 * 排序
 * @author Administrator
 */
@Data
public class Sort {

    /**
     * 排序方式
     */
    private String orderType;

    /**
     * 排序字段
     */
    private String orderField;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public Sort(String orderType, String orderField) {
        this.orderType = orderType;
        this.orderField = orderField;
    }

    public Sort(String orderField){
        this.orderField = orderField;
        this.orderType = "desc";
    }
}
