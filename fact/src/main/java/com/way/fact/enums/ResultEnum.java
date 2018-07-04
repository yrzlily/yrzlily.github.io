package com.way.fact.enums;

/**
 * 设置异常返回信息
 * @author Administrator
 */

public enum ResultEnum {
    //未知异常
    UNKNOWN_ERROR(0 , "异常错误");

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
