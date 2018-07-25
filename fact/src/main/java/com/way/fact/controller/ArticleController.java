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
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author Administrator
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
     * 全部文章列表
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(ModelAndView view){
        view.setViewName("/article/index");
        return view;
    }

    /**
     * 添加文章视图
     * @param view
     * @return
     */
    @GetMapping("/add")
    public ModelAndView add(ModelAndView view){

        List<Cate> cate = cateDao.findAll(new Sort(Sort.Direction.DESC , "sort"));
        view.addObject("cate" , cate);
        view.setViewName("/article/add");
        return view;
    }

    /**
     * 编辑文章视图
     * @param view
     * @return
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
     * 文章分页列表查询
     * @param pageable
     * @param filter 过滤信息
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Object list(Pageable pageable, @RequestParam(required = false , value = "search") String filter){
        pageable = PageRequest.of(pageable.getPageNumber() - 1 , pageable.getPageSize());
        Page<Article> list = articleService.list(pageable , filter);
        for (Article article : list){
            SimpleDateFormat dateFormat = new SimpleDateFormat();
            article.setTimestamp(dateFormat.format(article.getTimestamp()));
        }
        return ResultUtils.layPage(list.getTotalPages() , list.getContent());
    }

    /**
     * 添加文章
     * @param article
     * @return
     * @throws IOException
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
     * 删除文章
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    @ResponseBody
    public Result delete(@PathVariable String id){
        articleDao.delete(id);
        return ResultUtils.success(id);
    }

    /**
     * 编辑文章
     * @param article
     * @return
     * @throws IOException
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
