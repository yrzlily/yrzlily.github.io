package com.way.fact.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 响应信息
 * @author yrz
 */
public class Result<T> implements Serializable {

    /**
     * 返回参数
     */
    private Integer code;
    /**
     * 回调信息
     */
    private String msg;
    /**
     * 回调数据
     */
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
