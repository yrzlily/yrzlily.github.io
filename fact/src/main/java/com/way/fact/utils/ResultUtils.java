package com.way.fact.utils;

import com.way.fact.bean.Layer;
import com.way.fact.bean.Result;
import com.way.fact.enums.UserEnum;

/**
 * @author Administrator
 */
public class ResultUtils {

    /**
     * 成功
     * @param object
     * @return
     */
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(1);
        result.setMsg("成功");
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
     * @param code
     * @param msg
     * @param count
     * @param data
     * @return
     */
    public static Object layPage(Integer code, String msg , Integer count , Object data){
        Layer layer = new Layer();
        layer.setCode(code);
        layer.setMsg(msg);
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
}
