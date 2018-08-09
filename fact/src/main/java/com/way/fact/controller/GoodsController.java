package com.way.fact.controller;

import com.way.fact.bean.goods.Goods;
import com.way.fact.bean.goods.GoodsContent;
import com.way.fact.bean.Result;
import com.way.fact.bean.type.Type;
import com.way.fact.dao.GoodsDao;
import com.way.fact.dao.TypeDao;
import com.way.fact.service.GoodsService;
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
import java.sql.Timestamp;
import java.util.Optional;

/**
 * @author yrz
 */
@RequestMapping("/goods")
@Controller
public class GoodsController {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private TypeDao typeDao;


    /**
     * 商品列表
     * @param view
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(ModelAndView view){
        view.setViewName("/goods/index");
        return view;
    }

    /**
     * 添加商品
     * @param view
     * @return
     */
    @GetMapping("/add")
    public ModelAndView add(ModelAndView view){
        view.setViewName("/goods/add");
        return view;
    }

    /**
     * 编辑商品
     * @param view
     * @return
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Integer id , ModelAndView view){
        view.setViewName("/goods/edit");

        Goods goods = goodsDao.findById(id).get();

        view.addObject("goods" , goods);
        return view;
    }


    /**
     * 商品分页接口
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(Pageable pageable , @RequestParam(value = "search" , required = false)String search){

        pageable = PageRequest.of(pageable.getPageNumber() -1 , pageable.getPageSize());

        Page<Goods> goods = goodsService.findAll(pageable , search);


        return ResultUtils.layPage(goods.getTotalElements() , goods.getContent());
    }

    /**
     * 多条件分页查询商品
     * @param pageable
     * @param name
     * @return
     */
    @PostMapping("/all")
    @ResponseBody
    public Result index(@PageableDefault(value = 7) Pageable pageable, @RequestParam(value = "name" , required = false) String name){
        Page<Goods> goods = goodsService.findAll(pageable,name);
        return ResultUtils.success(goods);
    }

    /**
     * 查询单个商品
     * @param id
     * @return
     */
    @GetMapping("/one/{id}")
    @ResponseBody
    public Result goods(@PathVariable("id") Integer id){
        Optional<Goods> goods = goodsDao.findById(id);
        return ResultUtils.success(goods);
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    @GetMapping("/del/{id}")
    @ResponseBody
    public Result delete(@PathVariable Integer id){
        goodsDao.deleteById(id);
        return ResultUtils.success(id);
    }

    /**
     * 获取商品类型
     * @param id
     * @return
     */
    @GetMapping("/type/{id}")
    @ResponseBody
    public Result typeGet(@PathVariable Integer id){
        Optional<Type> type = typeDao.findById(id);

        return  ResultUtils.success(type);
    }

    /**
     * 添加商品
     * @param goods
     * @param bindingResult
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(
            @Valid Goods goods ,
            BindingResult bindingResult ,
            GoodsContent goodsContent
    ){
        if(goods == null){
            return ResultUtils.error(10001 , bindingResult.getFieldError().getDefaultMessage());
        }

        goods.setName(goods.getName());
        goods.setNum(goods.getNum());
        goods.setSort(goods.getSort());
        goods.setImages(goods.getImages());
        goods.setPrice(goods.getPrice());
        goods.setDescription(goods.getDescription());
        goods.setStatus("0");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        goods.setUpdate_time(timestamp);
        goodsContent.setContent(goodsContent.getContent());
        goods.setContent(goodsContent);
        goodsDao.save(goods);
        System.out.println(goods.getId());

        return ResultUtils.success(goods);
    }

    /**
     * 编辑商品
     * @param goods
     * @param bindingResult
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result edit(Goods goods , BindingResult bindingResult , GoodsContent goodsContent ){

        if(goods == null){
            return ResultUtils.error(10001 , bindingResult.getFieldError().getDefaultMessage());
        }

        goods.setId(goods.getId());
        goods.setName(goods.getName());
        goods.setNum(goods.getNum());
        goods.setSort(goods.getSort());
        goods.setImages(goods.getImages());
        goods.setPrice(goods.getPrice());
        goods.setDescription(goods.getDescription());
        goods.setStatus(goods.getStatus());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        goods.setCats(goods.getCats());

        goods.setUpdate_time(timestamp);
        goodsContent.setId(goods.getContent().getId());
        goodsContent.setContent(goodsContent.getContent());
        goods.setContent(goodsContent);



        goodsDao.save(goods);

        return ResultUtils.success(goods);
    }

}
