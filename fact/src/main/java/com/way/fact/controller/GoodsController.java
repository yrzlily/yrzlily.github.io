package com.way.fact.controller;

import com.way.fact.bean.Goods;
import com.way.fact.bean.Result;
import com.way.fact.bean.Type;
import com.way.fact.dao.GoodsDao;
import com.way.fact.dao.TypeDao;
import com.way.fact.service.GoodsService;
import com.way.fact.utils.RedisUtils;
import com.way.fact.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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

    @RequestMapping("/list")
    @ResponseBody
    public Result list(){

        Object goods = goodsDao.findAll();

        return ResultUtils.success(goods);
    }

    /**
     * 多条件分页查询商品
     * @param pageable
     * @param good
     * @return
     */
    @PostMapping("/all")
    public Result index(@PageableDefault(value = 7) Pageable pageable, @Valid Goods good){
        Page<Goods> goods = goodsService.findAll(pageable,good);
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

}
