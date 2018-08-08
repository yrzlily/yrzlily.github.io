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
 * @author Administrator
 */
@Controller
@RequestMapping("/cate")
public class CateController {

    @Autowired
    private CateDao cateDao;

    @Autowired
    private CateService cateService;

    /**
     * 列表视图
     * @param view
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(ModelAndView view){
        view.setViewName("/cate/index");
        return view;
    }

    /**
     * 添加分类视图
     * @param view
     * @return
     */
    @GetMapping("/add")
    public ModelAndView add(ModelAndView view){
        view.setViewName("/cate/add");
        return view;
    }

    /**
     * 编辑分类视图
     * @param view
     * @param id
     * @return
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(ModelAndView view , @PathVariable Integer id){
        Cate cate = cateDao.findById(id).get();
        view.addObject("cate" , cate);
        view.setViewName("/cate/edit");
        return  view;
    }

    /**
     * 请求分类
     * @param pageable
     * @param cate
     * @return
     */
    @ResponseBody
    @PostMapping("/list")
    public Object list(@PageableDefault(size = 15)Pageable pageable , Cate cate){
        pageable = PageRequest.of(pageable.getPageNumber() - 1 , pageable.getPageSize());
        Page<Cate> list = cateService.findAll(pageable , cate);
        return ResultUtils.layPage(list.getTotalElements() , list.getContent());
    }

    /**
     * 添加分类
     * @param cate
     * @param bindingResult
     * @return
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
     * 删除分类
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("/delete/{id}")
    public Result del(@PathVariable Integer id){
        cateDao.deleteById(id);
        return ResultUtils.success(id);
    }
}
