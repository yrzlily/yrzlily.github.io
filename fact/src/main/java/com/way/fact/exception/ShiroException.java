package com.way.fact.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Shiro权限异常处理
 * @author yrz
 */
public class ShiroException extends UnauthorizedException {

    private Integer code;

    public ShiroException(Integer code , String msg) {
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
