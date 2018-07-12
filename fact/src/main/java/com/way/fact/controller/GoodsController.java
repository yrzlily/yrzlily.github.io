package com.way.fact.controller;

import com.way.fact.bean.Goods;
import com.way.fact.bean.Result;
import com.way.fact.bean.Type;
import com.way.fact.dao.GoodsDao;
import com.way.fact.dao.TypeDao;
import com.way.fact.service.GoodsService;
import com.way.fact.service.impl.GoodsServiceImpl;
import com.way.fact.utils.RedisUtils;
import com.way.fact.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 */
@RequestMapping("/goods")
@RestController
public class GoodsController {

    @Autowired
    private GoodsDao goodsDao;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取所有商品
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Result list(){
        Object goods = goodsDao.findAll();
        return ResultUtils.success(goods);
    }

    /**
     * 多条件分页查询商品
     * @param pageable
     * @param name
     * @return
     */
    @PostMapping("/all")
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
    public Result add(
            @Valid Goods goods ,
            BindingResult bindingResult,
            @RequestParam(value = "type" , required = false) List<Type> list
    ){
        if(goods == null){
            return ResultUtils.error(10001 , bindingResult.getFieldError().getDefaultMessage());
        }

        goods.setName(goods.getName());
        goods.setSort(goods.getSort());

        //添加分类
        goods.setTypeList(list);

        goodsDao.save(goods);
        return ResultUtils.success(goods);
    }

    /**
     * 编辑商品
     * @param goods
     * @param bindingResult
     * @return
     */
    @PostMapping("/edit")
    public Result edit(@Valid Goods goods , BindingResult bindingResult){

        if(goods == null){
            return ResultUtils.error(10002 , bindingResult.getFieldError().getDefaultMessage());
        }

        goods.setId(goods.getId());
        goods.setName(goods.getName());
        goods.setSort(goods.getSort());

        goodsDao.save(goods);
        return ResultUtils.success(goods);
    }

}
