package com.way.fact.controller;

import com.way.fact.bean.Nav;
import com.way.fact.bean.Result;
import com.way.fact.config.CosConfig;
import com.way.fact.dao.NavDao;
import com.way.fact.service.NavService;
import com.way.fact.utils.FileUtils;
import com.way.fact.utils.RedisUtils;
import com.way.fact.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
     * 添加
     */
    @PostMapping("/add")
    public Object addNav(
            @Valid Nav nav ,
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request,
            BindingResult bindingResult
    ) throws IOException {

        if(nav==null){
            return ResultUtils.error(-1,bindingResult.getFieldError().getDefaultMessage());
        }

        String images = cosConfig.cosFileUpload(file , request);

        nav.setImages(images);
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
            @Valid Nav nav,
            @RequestParam("file") MultipartFile file,
            BindingResult bindingResult,
            HttpServletRequest request
            ) throws IOException {
        if(nav==null){
            return ResultUtils.error(-1,bindingResult.getFieldError().getDefaultMessage());
        }

        String images = fileUtils.upload(file , request);

        nav.setImages(images);
        nav.setId(nav.getId());
        nav.setName(nav.getName());
        nav.setSort(nav.getSort());
        nav.setParentId(nav.getParentId());
        navService.editNav(nav);
        return ResultUtils.success(nav);
    }

    /**
     * 删除
     */
    @PostMapping("/del")
    public Object delNav(@Valid Nav nav, BindingResult bindingResult){
        Optional<Nav> navs = navService.delNav(nav.getId());

        if(navs==null){
            return ResultUtils.error(-2,bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtils.success(navs);
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

            list = navService.findAllByParentId(navDao.findAll() , 0);
            redisUtils.forList("nav" , list);
            redisUtils.setKeyLifeTime("nav" , 1 , TimeUnit.HOURS);

        }

        return ResultUtils.success(list);
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

}
