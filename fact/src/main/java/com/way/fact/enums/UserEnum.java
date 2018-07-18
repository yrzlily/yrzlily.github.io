package com.way.fact.enums;

import lombok.Getter;

/**
 * 用户错误信息
 * @author Administrator
 */
@Getter
public enum UserEnum {
    //数据为空
    NULL_ERROR(10001 , "数据为空");

    private Integer code;
    private String msg;

    UserEnum(Integer code, String msg) {
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
