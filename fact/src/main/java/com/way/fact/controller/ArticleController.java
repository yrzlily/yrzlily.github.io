package com.way.fact.controller;

import com.way.fact.bean.Cate;
import com.way.fact.bean.Result;
import com.way.fact.bean.User;
import com.way.fact.dao.CateDao;
import com.way.fact.mongo.Article;
import com.way.fact.mongodao.ArticleDao;
import com.way.fact.mongoservice.ArticleService;
import com.way.fact.utils.ResultUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author yrz
 */
@RequestMapping("/article")
@Controller
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CateDao cateDao;

    /**
     * @return 全部文章列表
     */
    @GetMapping("/index")
    public ModelAndView index(ModelAndView view){
        view.setViewName("/article/index");

        List<Cate> cate  = cateDao.findAll();
        view.addObject("cate" , cate);
        return view;
    }

    /**
     *
     * @param view 视图
     * @return 添加文章视图
     */
    @GetMapping("/add")
    public ModelAndView add(ModelAndView view){

        List<Cate> cate = cateDao.findAll(new Sort(Sort.Direction.DESC , "sort"));
        view.addObject("cate" , cate);
        view.setViewName("/article/add");
        return view;
    }

    /**
     * @param view 视图
     * @return 编辑文章视图
     */
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable String id , ModelAndView view){
        Article article = articleDao.find(id);
        List<Cate> cate = cateDao.findAll(new Sort(Sort.Direction.DESC , "sort"));
        view.addObject("cate" , cate);
        view.addObject("article" , article);
        view.setViewName("/article/edit");
        return view;
    }

    /**
     * @param pageable 分页信息
     * @param search 过滤信息
     * @return 文章分页列表查询
     */
    @GetMapping("/list")
    @ResponseBody
    public Object list(Pageable pageable, @RequestParam(required = false , value = "search") String search , @RequestParam(required = false , value = "cate") Integer cate){
        pageable = PageRequest.of(pageable.getPageNumber() - 1 , pageable.getPageSize());
        Page<Article> list = articleService.list(pageable , search , cate);
        for (Article article : list){
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            article.setTimestamp(dateFormat.format(article.getTimestamp()));
        }
        return ResultUtils.layPage(list.getTotalElements() , list.getContent());
    }

    /**
     * 添加文章
     * @param article 文章信息实体
     * @return 文章分页
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(Article article) {


        //获取用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        article.setBy(user.getUsername());
        article.setTitle(article.getTitle());
        article.setDescription(article.getDescription());
        article.setImages(article.getImages());
        article.setContent(article.getContent());
        article.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        articleDao.add(article);
        return ResultUtils.success(article);
    }

    /**
     * @param id 文章id
     * @return 删除文章
     */
    @GetMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable String id){
        articleDao.delete(id);
        return ResultUtils.success(id);
    }

    /**
     * @param article 文章实体
     * @return 修改文章
     */
    @ResponseBody
    @PostMapping("/edit")
    public Result edit(Article article) {
        //获取用户信息
        User user = (User) SecurityUtils.getSubject().getPrincipal();

        article.setId(article.getId());
        article.setBy(user.getUsername());
        article.setTitle(article.getTitle());
        article.setDescription(article.getDescription());
        article.setImages(article.getImages());
        article.setContent(article.getContent());
        article.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        articleDao.edit(article);
        return ResultUtils.success(article);
    }
}
