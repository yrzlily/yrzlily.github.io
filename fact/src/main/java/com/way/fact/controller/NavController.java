package com.way.fact.controller;

import com.way.fact.bean.Nav;
import com.way.fact.bean.Result;
import com.way.fact.bean.Sort;
import com.way.fact.config.CosConfig;
import com.way.fact.dao.NavDao;
import com.way.fact.service.NavService;
import com.way.fact.service.impl.NavServiceImpl;
import com.way.fact.utils.FileUtils;
import com.way.fact.utils.RedisUtils;
import com.way.fact.utils.ResultUtils;
import com.way.fact.utils.SortUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/nav")
public class NavController {

    @Autowired
    private NavService navService;

    @Autowired
    private NavDao navDao;

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    private CosConfig cosConfig;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 导航设置
     * @param view
     * @return
     */
    @RequiresRoles("admin")
    @GetMapping("/index")
    public ModelAndView index(ModelAndView view){
        view.setViewName("/nav/index");
        return view;
    }

    /**
     * 添加
     */
    @PostMapping("/add")
    public Object addNav(
            @RequestBody @Valid Nav nav ,
            BindingResult bindingResult
    )  {

        if(bindingResult.hasErrors()){
            return ResultUtils.error(-1,bindingResult.getFieldError().getDefaultMessage());
        }


        nav.setImages(nav.getImages());
        nav.setName(nav.getName());
        nav.setSort(nav.getSort());
        nav.setParentId(nav.getParentId());
        navService.addNav(nav);
        return ResultUtils.success(nav);
    }

    /**
     * 修改
     * @param nav
     * @param bindingResult
     * @return
     */
    @PostMapping("/edit")
    public Object editNav(
            @RequestBody @Valid Nav nav,
            BindingResult bindingResult
            )  {
        if(bindingResult.hasErrors()){
            return ResultUtils.error(-1,bindingResult.getFieldError().getDefaultMessage());
        }

        nav.setImages(nav.getImages());
        nav.setId(nav.getId());
        nav.setName(nav.getName());
        nav.setUrl(nav.getUrl());
        nav.setSort(nav.getSort());
        nav.setParentId(nav.getParentId());
        navService.editNav(nav);
        return ResultUtils.success(nav);
    }

    /**
     * 删除
     */
    @PostMapping("/del")
    public Object delNav(@RequestBody Nav nav){
        Optional<Nav> obj = navService.delNav(nav.getId());
        return ResultUtils.success(obj);
    }

    /**
     * 遍历所有
     */
    @GetMapping("/list")
    public List<Nav> list(){
        List<Nav> list = navService.findALL();
        if(list != null){
            return list;
        }else{
            return null;
        }

    }

    /**
     * 单个查询
     */
    @GetMapping("/info/{id}")
    public Optional<Nav> findOne(@PathVariable Integer id){
        return navService.findOne(id);
    }

    /**
     * 通过父ID递归
     */
    @GetMapping("/parentId/{parentID}")
    public Result findParentID(@PathVariable Integer parentID ){

        //缓存内容是否为空
        long sizeList = redisUtils.sizeList("nav");
        Object list;
        if(sizeList > 0){

            list = redisUtils.getList("nav" , 0 , -1);
        }else{

            list = navService.findAllByParentId(navDao.findAll(SortUtils.basicSort(new Sort("asc","sort"))) , parentID);
            redisUtils.forList("nav" , list);
            redisUtils.setKeyLifeTime("nav" , 10 , TimeUnit.SECONDS);

        }

        return ResultUtils.success(list);
    }

    /**
     * 移动节点
     * @param nav
     * @return
     */
    @PostMapping("/move")
    public Result move(@RequestBody Nav nav){

        nav.setName(nav.getName());
        nav.setId(nav.getId());
        nav.setSort(nav.getSort());
        nav.setUrl(nav.getUrl());
        nav.setParentId(nav.getParentId());
        navService.editNav(nav);
        return ResultUtils.success(nav);
    }

    /**
     * 上传图片
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file , HttpServletRequest request) throws IOException {
        String url = fileUtils.upload(file , request);
        return ResultUtils.success(url);
    }

    /**
     * 模糊查询
     */
    @PostMapping("/like")
    public Result like(@RequestParam("name") String like){
        List<Nav> nav = navDao.findByNameLike("%"+like+"%");

        return ResultUtils.success(nav);
    }

    /**
     * 寻找父节点
     */
    @GetMapping("/child/{id}")
    public Object child(@PathVariable Integer id)  {
        Nav nav = navDao.findById(id).get();
        Object navs = navService.findAllByChild(nav );
        return ResultUtils.success(navs);
    }

}
