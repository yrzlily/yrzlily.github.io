package com.way.fact.handle;

import com.way.fact.bean.Result;
import com.way.fact.enums.ResultEnum;
import com.way.fact.enums.UserEnum;
import com.way.fact.exception.ShiroException;
import com.way.fact.utils.ResultUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

/**
 * 统一异常处理
 * @author yrz
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
    public Object handle(Exception e){

        if(e instanceof ShiroException){
            ShiroException shiroException = (ShiroException) e;
            return ResultUtils.error(shiroException.getCode() , shiroException.getMessage());
        }else if(e instanceof DataIntegrityViolationException){
            return ResultUtils.error(ResultEnum.VIOLATION_EXCEPTION);
        }else if(e instanceof UnauthorizedException){
            return new ModelAndView("/login/unAuth");
        }

        return ResultUtils.error(ResultEnum.UNKNOWN_ERROR);
    }

}
