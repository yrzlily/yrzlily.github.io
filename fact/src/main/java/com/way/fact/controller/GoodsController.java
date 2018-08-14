package com.way.fact.controller;

import com.way.fact.bean.goods.Goods;
import com.way.fact.bean.goods.GoodsAttr;
import com.way.fact.bean.goods.GoodsContent;
import com.way.fact.bean.Result;
import com.way.fact.bean.goods.GoodsSpec;
import com.way.fact.bean.type.Type;
import com.way.fact.bean.type.TypeAttr;
import com.way.fact.dao.GoodAttrDao;
import com.way.fact.dao.GoodsDao;
import com.way.fact.dao.GoodsSpecDao;
import com.way.fact.dao.TypeDao;
import com.way.fact.service.GoodsService;
import com.way.fact.service.TypeAttrService;
import com.way.fact.utils.JsonListUtil;
import com.way.fact.utils.ResultUtils;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
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
import java.text.DecimalFormat;
import java.util.List;
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

    @Autowired
    private GoodAttrDao goodAttrDao;

    @Autowired
    private TypeAttrService typeAttrService;

    @Autowired
    private GoodsSpecDao goodsSpecDao;

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
     * 规格属性视图
     * @param view
     * @param cate
     * @param gid
     * @return
     */
    @RequestMapping("/attr/{cate}/{gid}")
    public ModelAndView view(ModelAndView view , @PathVariable("cate")Integer cate , @PathVariable("gid")Integer gid){

        Type type = typeDao.findById(cate).get();
        List<GoodsAttr> goodsAttrList = goodAttrDao.findByGoodsAttrGid(gid);

        for (GoodsAttr goodsAttr : goodsAttrList){
            System.out.println(goodsAttr.getGoodsAttrName());
        }

        System.out.println(type.getTypeName());
        for (TypeAttr typeAttr : type.getTypeAttrs()){
            System.out.println(typeAttr.getTypeAttributesName());
        }

        view.addObject("typeAttrList" , type.getTypeAttrs());
        view.addObject("goodsAttrList" , goodsAttrList);
        view.addObject("gid" , gid);
        view.setViewName("/goods/attr");
        return view;
    }

    @GetMapping("/specifications/{cate}/{gid}")
    public ModelAndView specifications(ModelAndView view , @PathVariable("cate")Integer cate , @PathVariable("gid")Integer gid){

        Type type = typeDao.findById(cate).get();
        List<GoodsAttr> goodsAttrList = goodAttrDao.findByGoodsAttrGid(gid);

        List<GoodsSpec> goodsSpec = goodsSpecDao.findAllByGid(gid);


        for (GoodsSpec g : goodsSpec){
            g.setList(JsonListUtil.jsonToList(g.getSpec() , Integer.class));

            List<GoodsAttr> goodsAttrs = goodAttrDao.findAllByList(g.getList());
            g.setGoodsAttrs(goodsAttrs);
        }


        view.addObject("typeAttrList" , type.getTypeAttrs());
        view.addObject("goodsSpec" , goodsSpec);
        view.addObject("goodsAttrList" , goodsAttrList);
        view.addObject("gid" , gid);

        view.setViewName("/goods/specifications");
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


    /**
     * 更新规格属性
     * @param goodsAttrs
     * @return
     */
    @ResponseBody
    @PostMapping("/attrSave")
    public Result attrSave( GoodsAttr goodsAttrs){

        List<GoodsAttr> goodsAttrList = goodsAttrs.getGoodsAttrs();

        for (GoodsAttr goodsAttr:goodsAttrList){

            goodsAttr.setGoodsAttrName(goodsAttr.getGoodsAttrName());
            goodsAttr.setGoodsAttrGid(goodsAttr.getGoodsAttrGid());
            goodsAttr.setGoodsAttrPrice(goodsAttr.getGoodsAttrPrice());
            goodsAttr.setGoodsAttrTid(goodsAttr.getGoodsAttrTid());

            if(goodsAttr.getId() != null){
                goodsAttr.setId(goodsAttr.getId());
                System.out.println(goodsAttr.getGoodsAttrName());
            }

            goodAttrDao.save(goodsAttr);
        }

        return ResultUtils.success(goodsAttrs);
    }

    /**
     * 删除属性
     * @param id
     * @return
     */
    @PostMapping("/attrDel/{id}")
    @ResponseBody
    public Result attrDel(@PathVariable("id")Integer id){
        goodAttrDao.deleteById(id);
        return ResultUtils.success(id);
    }

    /**
     * 储存规格
     * @param goodsSpec
     * @return
     */
    @ResponseBody
    @PostMapping("/addSpec")
    public Result addSpec(@Valid GoodsSpec goodsSpec ){


        String json = JSONArray.fromObject(goodsSpec.getList()).toString();

        goodsSpec.setGid(goodsSpec.getGid());
        goodsSpec.setSpec(json);
        goodsSpec.setNum(goodsSpec.getNum());

        goodsSpecDao.save(goodsSpec);

        return ResultUtils.success(goodsSpec);
    }

    /**
     * 修改库存
     * @param id
     * @param num
     * @return
     */
    @PostMapping("/editSpec")
    @ResponseBody
    public Result editSpec(@RequestParam("id") Integer id , @RequestParam("num")Integer num){

        goodsSpecDao.updateNum(id , num);
        return ResultUtils.success(num);
    }

    /**
     * 删除匹配
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("/delSpec/{id}")
    public Result delSpec(@PathVariable("id")Integer id){
        goodsSpecDao.deleteById(id);

        return ResultUtils.success(id);
    }

}
