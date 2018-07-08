package com.way.fact.utils;

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

    public static Object error(UserEnum nullError) {

        return nullError;
    }
}
