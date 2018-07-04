package com.way.fact.controller;

import com.way.fact.bean.Article;
import com.way.fact.bean.Book;
import com.way.fact.bean.Result;
import com.way.fact.dao.ArticleDao;
import com.way.fact.dao.BookDao;
import com.way.fact.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Administrator
 */
@RequestMapping("/article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private BookDao bookDao;

    @GetMapping("/find/{id}")
    public Result find(@PathVariable("id") Integer id){

        Optional<Article> article = articleDao.findById(id);

        return ResultUtils.success(article);
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam("id") Integer id){
        Optional<Article> article = articleDao.findById(id);
        articleDao.deleteById(id);

        return ResultUtils.success(article);
    }

    @GetMapping("/all")
    public Result index(){

        List<Article> list = articleDao.findAll();

        return ResultUtils.success(list);
    }
}
