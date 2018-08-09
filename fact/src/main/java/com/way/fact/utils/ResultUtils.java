package com.way.fact.utils;

import com.way.fact.bean.Layer;
import com.way.fact.bean.Result;
import com.way.fact.enums.ResultEnum;
import com.way.fact.enums.UserEnum;

/**
 * @author yrz
 */
public class ResultUtils {

    /**
     * 成功
     * @param object
     * @return
     */
    public static Result success( Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 失败
     */
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * lay参数
     * @param count
     * @param data
     * @return
     */
    public static Object layPage( Long count , Object data){
        Layer layer = new Layer();
        layer.setCode(ResultEnum.SUCCESS.getCode());
        layer.setMsg(ResultEnum.SUCCESS.getMsg());
        layer.setCount(count);
        layer.setData(data);
        return layer;
    }

    /**
     * 错误信息
     * @param nullError
     * @return
     */
    public static Object error(UserEnum nullError) {
        return nullError;
    }

    public static Result error(ResultEnum violationException) {
        Result result = new Result();
        result.setCode(violationException.getCode());
        result.setMsg(violationException.getMsg());
        return result;
    }
}
