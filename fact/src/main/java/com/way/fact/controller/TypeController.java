package com.way.fact.controller;

import com.way.fact.bean.Result;
import com.way.fact.bean.Type;
import com.way.fact.dao.TypeDao;
import com.way.fact.service.TypeService;
import com.way.fact.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 商品分类控制器
 * @author Administrator
 */
@RequestMapping("/type")
@Controller
public class TypeController {

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private TypeService typeService;

    /**
     * 分类列表视图
     * @param view
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(ModelAndView view , @RequestParam(value = "pid" ,required = false, defaultValue = "0")Integer pid){

        if(pid!=0){
            Type type = typeService.parent(pid);
            view.addObject("type" , type);
        }

        view.addObject("pid" , pid);
        view.setViewName("/type/index");
        return view;
    }

    /**
     * 添加视图
     * @param view
     * @return
     */
    @GetMapping("/add")
    public ModelAndView add(ModelAndView view , @RequestParam(value = "pid" ,  defaultValue = "0")Integer pid){
        view.addObject("pid" , pid);
        view.setViewName("/type/add");
        return view;
    }

    /**
     * 相应分页列表
     * @param pageable
     * @param search
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object list(Pageable pageable , @RequestParam(value = "search" , required = false)String search , @RequestParam(value = "pid" , required = false)Integer pid){

        pageable = PageRequest.of(pageable.getPageNumber() - 1 , pageable.getPageSize());

        Page<Type> types = typeService.findAll(pageable , search , pid);

        return ResultUtils.layPage(types.getTotalElements() , types.getContent());
    }

    @PostMapping("/add")
    @ResponseBody
    public Result add(@Valid Type type , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtils.error(10005 , bindingResult.getFieldError().getDefaultMessage());
        }

        type.setTypeName(type.getTypeName());
        type.setImages(type.getImages());
        type.setSort(type.getSort());
        type.setParentID(type.getParentID());
        typeDao.save(type);
        return ResultUtils.success(type);
    }

    @PostMapping("/del/{id}")
    @ResponseBody
    public Result del(@PathVariable("id")Integer id){

        Integer sum = typeDao.countByParentID(id);

        if(sum>0){
            return ResultUtils.error(10005 , "该栏目下有子分类，不得删除");
        }

        typeDao.deleteById(id);
        return ResultUtils.success(id);
    }

}
