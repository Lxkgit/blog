package com.example.blog.controller;

import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.ArticleTypeService;
import com.example.blog.service.BlogArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lxk
 * @date: 2021/7/5 13:37
 * @description: 博客文章控制类
 * @modified By:
 */
@RestController
@ResponseBody
@RequestMapping("/article")
public class BlogArticleController {

    @Autowired
    private BlogArticleService blogArticleService;

    /**
     * 查询文章列表
     * @param size
     * @param page
     * @return
     */
    @GetMapping("/find/list/{size}/{page}")
    public Result selectArticleList(@PathVariable("size") int size, @PathVariable("page") int page) {
        System.out.println("size: " + size + "  page: " + page);
        return ResultFactory.buildSuccessResult(blogArticleService.selectArticleList(page, size));
    }

    /**
     * 通过文章id查询文章
     * @param id
     * @return
     */
    @GetMapping("/find/id/{id}")
    public Result selectArticleById(@PathVariable("id") int id) {
        return ResultFactory.buildSuccessResult(blogArticleService.selectArticleById(id));
    }
}
