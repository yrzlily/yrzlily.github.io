package com.way.fact.controller;

import com.way.fact.bean.Cate;
import com.way.fact.bean.Result;
import com.way.fact.dao.CateDao;
import com.way.fact.service.CateService;
import com.way.fact.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * 分类控制器
 * @author yrz
 */
@Controller
@RequestMapping("/cate")
public class CateController {

    @Autowired
    private CateDao cateDao;

    @Autowired
    private CateService cateService;

    /**
     * @param view 视图
     * @return 列表视图
     */
    @GetMapping("/index")
    public ModelAndView index(ModelAndView view){
        view.setViewName("/cate/index");
        return view;
    }

    /**
     * @param view 视图
     * @return 添加分类视图
     */
    @GetMapping("/add")
    public ModelAndView add(ModelAndView view){
        view.setViewName("/cate/add");
        return view;
    }

    /**
     *
     * @param view 视图
     * @param id 编辑id
     * @return 编辑分类视图
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(ModelAndView view , @PathVariable Integer id){
        Cate cate = cateDao.findById(id).get();
        view.addObject("cate" , cate);
        view.setViewName("/cate/edit");
        return  view;
    }

    /**
     * @param pageable 分页类型
     * @param cate 分类
     * @return 请求分类
     */
    @ResponseBody
    @PostMapping("/list")
    public Object list(@PageableDefault(size = 15)Pageable pageable , Cate cate){
        pageable = PageRequest.of(pageable.getPageNumber() - 1 , pageable.getPageSize());
        Page<Cate> list = cateService.findAll(pageable , cate);
        return ResultUtils.layPage(list.getTotalElements() , list.getContent());
    }

    /**
     *
     * @param cate 分类实体
     * @param bindingResult 错误信息
     * @return 添加分类
     */
    @ResponseBody
    @PostMapping("/add")
    public Result add(@Valid Cate cate , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtils.error(1003 , bindingResult.getFieldError().getDefaultMessage());
        }

        cate.setName(cate.getName());
        cate.setSort(cate.getSort());
        cate.setParentId(0);
        cateDao.save(cate);
        return ResultUtils.success(cate);
    }

    /**
     *
     * @param cate 分类实体
     * @return 编辑分类
     */
    @PostMapping("/edit")
    public Result edit(@Valid Cate cate){

        cate.setName(cate.getName());
        cate.setSort(cate.getSort());
        cate.setParentId(0);
        cate.setId(cate.getId());
        cateDao.save(cate);
        return ResultUtils.success(cate);
    }

    /**
     *
     * @param id 删除id
     * @return 删除分类
     */
    @ResponseBody
    @GetMapping("/delete/{id}")
    public Result del(@PathVariable Integer id){
        cateDao.deleteById(id);
        return ResultUtils.success(id);
    }
}
