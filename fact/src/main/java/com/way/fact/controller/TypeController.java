package com.way.fact.controller;

import com.way.fact.bean.Result;
import com.way.fact.bean.type.Type;
import com.way.fact.bean.type.TypeAttr;
import com.way.fact.dao.TypeAttrDao;
import com.way.fact.dao.TypeDao;
import com.way.fact.service.TypeAttrService;
import com.way.fact.service.TypeService;
import com.way.fact.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 商品分类控制器
 * @author yrz
 */
@RequestMapping("/type")
@Controller
public class TypeController {

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private TypeAttrDao typeAttrDao;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TypeAttrService typeAttrService;

    /**
     *
     * @param view 视图
     * @return 分类列表视图
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
     *
     * @param view 视图
     * @return 添加视图
     */
    @GetMapping("/add")
    public ModelAndView add(ModelAndView view , @RequestParam(value = "pid" ,  defaultValue = "0")Integer pid){
        view.addObject("pid" , pid);
        view.setViewName("/type/add");
        return view;
    }

    /**
     *
     * @param view 视图
     * @return 编辑视图
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(ModelAndView view , @PathVariable("id")Integer id){
        Type type = typeDao.findById(id).get();
        view.addObject("type" , type);
        view.setViewName("/type/edit");
        return view;
    }

    /**
     *
     * @param view 视图
     * @param tid 分类id
     * @return 规格列表视图
     */
    @GetMapping("/attributes/{tid}")
    public ModelAndView attributes(ModelAndView view , @PathVariable("tid")Integer tid){

        view.addObject("tid" , tid);

        view.setViewName("/type/attributes");
        return view;
    }

    /**
     *
     * @param view 视图
     * @param tid 分类id
     * @return 添加属性视图
     */
    @GetMapping("/attrAdd")
    public ModelAndView attrAdd(ModelAndView view , @RequestParam("tid")Integer tid){

        view.addObject("tid" , tid);
        view.setViewName("/type/attradd");
        return view;
    }

    /**
     *
     * @param view 视图
     * @param id 属性id
     * @return 编辑属性视图
     */
    @GetMapping("/attrEdit/{id}")
    public ModelAndView attrEdit(ModelAndView view , @PathVariable("id") Integer id){

        TypeAttr typeAttr = typeAttrDao.findById(id).get();
        view.addObject("typeAttr" , typeAttr);
        view.setViewName("/type/attredit");
        return view;
    }

    /**
     *
     * @param pageable 分页信息
     * @param search 搜索条件
     * @return 相应分页列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object list(Pageable pageable , @RequestParam(value = "search" , required = false)String search , @RequestParam(value = "pid" , required = false)Integer pid){
        List<Sort.Order> orders = new ArrayList<>();
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC , "sort");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC , "id");
        orders.add(0 , order1);
        orders.add(1 , order2);

        pageable = PageRequest.of(pageable.getPageNumber() - 1 , pageable.getPageSize()  , Sort.by(orders));

        Page<Type> types = typeService.findAll(pageable , search , pid);

        return ResultUtils.layPage(types.getTotalElements() , types.getContent());
    }

    /**
     *
     * @return 递归子类
     */
    @ResponseBody
    @PostMapping("/tree")
    public Result tree(){
        List<Type> types = typeService.child(typeDao.findAll() , 0);

        return ResultUtils.success(types);
    }

    /**
     *
     * @param type 分类实体
     * @param bindingResult 错误信息
     * @return 添加分类
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(@Valid Type type , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtils.error(10005 , Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        type.setTypeName(type.getTypeName());
        type.setImages(type.getImages());
        type.setSort(type.getSort());
        type.setParentID(type.getParentID());
        typeDao.save(type);
        return ResultUtils.success(type);
    }

    /**
     *
     * @param id 分类id
     * @return 删除分类
     */
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

    /**
     *
     * @param type 分类实体
     * @param bindingResult 错误信息
     * @return 编辑分类
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result edit(@Valid Type type , BindingResult bindingResult ){
        if(bindingResult.hasErrors()){
            return ResultUtils.error(10005 , Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        type.setId(type.getId());
        type.setImages(type.getImages());
        type.setSort(type.getSort());

        typeDao.save(type);

        return ResultUtils.success(type);
    }

    /**
     *
     * @param tid 分类id
     * @param pageable 分页信息
     * @return 属性列表
     */
    @ResponseBody
    @RequestMapping("/attributesList/{tid}")
    public Object attributesList(@PathVariable("tid")Integer tid , Pageable pageable){

        List<Sort.Order> orders = new ArrayList<>();

        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC , "id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC , "sort");
        orders.add(0,order2);
        orders.add(1,order1);


        pageable = PageRequest.of(pageable.getPageNumber() - 1 , pageable.getPageSize() , Sort.by(orders));

        Page<TypeAttr> page = typeAttrService.findAll(pageable , tid);

        return ResultUtils.layPage(page.getTotalElements() , page.getContent());
    }

    /**
     *
     * @param typeAttr 属性实体
     * @param bindingResult 错误信息
     * @return 添加属性
     */
    @ResponseBody
    @PostMapping("/attrAdd")
    public Result attrAdd(@Valid TypeAttr typeAttr , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtils.error(10005 , Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        typeAttr.setTypeAttributesName(typeAttr.getTypeAttributesName());
        typeAttr.setTypeAttributesType(typeAttr.getTypeAttributesType());
        typeAttr.setSort(typeAttr.getSort());
        typeAttr.setTid(typeAttr.getTid());
        typeAttrDao.save(typeAttr);

        return ResultUtils.success(typeAttr);
    }

    /**
     *
     * @param typeAttr 属性实体
     * @param bindingResult 错误信息
     * @return  编辑属性
     */
    @ResponseBody
    @PostMapping("/attrEdit")
    public Result attrEdit(@Valid TypeAttr typeAttr , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtils.error(10005 , Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }

        typeAttr.setId(typeAttr.getId());
        typeAttr.setTypeAttributesName(typeAttr.getTypeAttributesName());
        typeAttr.setTypeAttributesType(typeAttr.getTypeAttributesType());
        typeAttr.setSort(typeAttr.getSort());
        typeAttr.setTid(typeAttr.getTid());
        typeAttrDao.save(typeAttr);

        return ResultUtils.success(typeAttr);
    }

    /**
     *
     * @param id 属性ID
     * @return 删除属性
     */
    @ResponseBody
    @GetMapping("/attrDel/{id}")
    public Result attrDel(@PathVariable("id")Integer id){
        typeAttrDao.deleteById(id);
        return ResultUtils.success(id);
    }

}
