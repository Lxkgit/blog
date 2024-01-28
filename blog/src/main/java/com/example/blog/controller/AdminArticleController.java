package com.example.blog.controller;

import com.example.blog.entity.Article;
import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.BlogArticleService;
import com.example.blog.util.Img;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: lxk
 * @date: 2021/7/5 13:37
 * @description: 博客文章控制类
 * @modified By:
 */
@RestController
@ResponseBody
@RequestMapping("/admin/content")
public class AdminArticleController {
    @Autowired
    private BlogArticleService blogArticleService;

    /**
     * 负责文章插入 由之前save接口拆分而成
     * @param article
     * @return
     */
    @PostMapping("/insert")
    public Result insertArticle(@RequestBody Article article) {
        blogArticleService.insertArticle(article);
        return ResultFactory.buildSuccessResult("文章保存成功");
    }

    @PostMapping("/update")
    public Result updateArticle(@RequestBody Article article) {
        System.out.println(article);
        blogArticleService.updateArticle(article);
        return ResultFactory.buildSuccessResult("文章保存成功");
    }

    /**
     * 查询博客文章，使用分页
     *
     * @param size
     * @param page
     * @return
     */
    @GetMapping("/find/list/{size}/{page}")
    public Result selectArticleList(@PathVariable("size") int size, @PathVariable("page") int page) {
        return ResultFactory.buildSuccessResult(blogArticleService.selectArticleList(page, size));
    }

    /**
     * 通过文章id查询文章
     *
     * @param id
     * @return
     */
    @GetMapping("/find/id/{id}")
    public Result selectArticleById(@PathVariable("id") int id) {
        return ResultFactory.buildSuccessResult(blogArticleService.selectArticleById(id));
    }

    /**
     * 通过文章id删除文章
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/id/{id}")
    public Result deleteArticleById(@PathVariable("id") int id) {
        blogArticleService.deleteArticleById(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }

    /**
     * 预留上传文章图片，暂不可用
     *
     * @param file
     * @return
     */
    @PostMapping("/img/upload")
    public Result uploadImg(MultipartFile file) {
        String url = Img.imgUpload("article", file);
        return ResultFactory.buildSuccessResult(url);
    }
}
