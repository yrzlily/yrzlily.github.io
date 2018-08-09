package com.way.fact.bean;

import lombok.Data;

/**
 * layer返回参数
 * @author yrz
 */
@Data
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

}
