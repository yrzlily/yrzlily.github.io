package com.fact.task.enums;

/**
 * 状态
 * @author yrz
 */

public enum Status {
    //订单状态
    NOT_PAY(0,"未支付"),
    IS_PAY(1,"已支付"),
    DELIVER(3,"已发货"),
    TAKE_GOODS(4,"已收货");


    private Integer code;

    private String msg;

    Status(Integer code, String msg) {
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
