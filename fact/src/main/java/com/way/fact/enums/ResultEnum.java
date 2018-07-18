package com.way.fact.enums;

import lombok.Getter;

/**
 * 设置异常返回信息
 * @author Administrator
 */
@Getter
public enum ResultEnum {
    //操作成功
    SUCCESS(0,"成功"),
    //未知异常
    UNKNOWN_ERROR(1000 , "异常错误"),
    //字段重复异常
    VIOLATION_EXCEPTION(1001,"权限标识不能重复"),
    //无权限异常
    POWER_INTO(1002 , "你没有权限操作这个地方");

    private Integer code;
    
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;

    }

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
}
