package com.way.fact.bean;

import lombok.Data;

/**
 * layer返回参数
 * @author yrz
 */
public class Layer {

    /**
     * 返回参数
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回页码
     */
    private Long count;
    /**
     * 返回数据
     */
    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
