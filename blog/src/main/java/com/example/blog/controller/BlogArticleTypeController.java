package com.example.blog.controller;

import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lxk
 * @time: 2021/10/8 19:31
 * @description :博客文章类型接口
 */
@RestController
@ResponseBody
@RequestMapping("/label")
public class BlogArticleTypeController {

    @Autowired
    private ArticleTypeService articleTypeService;

    /**
     * 查询文章类型列表
     * @return
     */
    @GetMapping("/select/type")
    public Result selectArticleTypeList(){
        return ResultFactory.buildSuccessResult(articleTypeService.selectArticleTypeList());
    }

    /**
     * 查询文章类型列表
     * @return
     */
    @GetMapping("/select/tree")
    public Result selectArticleTypeTree(){
        return ResultFactory.buildSuccessResult(articleTypeService.selectArticleTypeTree());
    }

    /**
     * 通过id查询文章类型
     * @param id
     * @return
     */
    @GetMapping("/select/type/{id}")
    public Result selectArticleTypeById(@PathVariable("id") int id){
        return ResultFactory.buildSuccessResult(articleTypeService.selectArticleTypeById(id));
    }
}
