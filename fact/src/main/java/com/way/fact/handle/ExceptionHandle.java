package com.way.fact.handle;

import com.way.fact.bean.Result;
import com.way.fact.exception.ShiroException;
import com.way.fact.utils.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * @author Administrator
 */
@ControllerAdvice
public class ExceptionHandle {

    /**
     * 抛出异常信息
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){

        if(e instanceof ShiroException){
            ShiroException shiroException = (ShiroException) e;
            return ResultUtils.error(shiroException.getCode() , shiroException.getMessage());
        }

        return ResultUtils.error(-101 , e.getMessage());
    }

}
