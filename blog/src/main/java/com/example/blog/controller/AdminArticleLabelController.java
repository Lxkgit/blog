package com.example.blog.controller;

import com.example.blog.entity.ArticleLabel;
import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.ArticleLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: lxk
 * @date: 2022/1/12 19:52
 * @description: 文章标签控制类
 * @modified By:
 */
@RestController
@ResponseBody
@RequestMapping("/admin/l")
public class AdminArticleLabelController {

    @Autowired
    private ArticleLabelService articleLabelService;

    @GetMapping("/select/list")
    public Result selectArticleLabelList(){
        return ResultFactory.buildSuccessResult(articleLabelService.selectArticleLabelList());
    }

    @GetMapping("/select/{id}")
    public Result selectArticleLabelById(@PathVariable("id") Integer id){
        return ResultFactory.buildSuccessResult(articleLabelService.selectArticleLabelById(id));

    }

    @GetMapping("/select/list/{labelTypeId}")
    public Result selectArticleLabelByLabelTypeId(@PathVariable("labelTypeId") Integer id){
        return ResultFactory.buildSuccessResult(articleLabelService.selectArticleLabelByLabelTypeId(id));

    }

    @PostMapping("/insert")
    public Result insertArticleLabel(@RequestBody ArticleLabel articleLabel){
        articleLabelService.insertArticleLabel(articleLabel);
        return ResultFactory.buildSuccessResult("标签创建成功");
    }

    @PostMapping("/update")
    public Result updateArticleLabel(@RequestBody ArticleLabel articleLabel){
        articleLabelService.updateArticleLabel(articleLabel);
        return ResultFactory.buildSuccessResult("标签修改成功");
    }

    @GetMapping("/delete/{id}")
    public Result deleteArticleLabelById(@PathVariable("id") Integer id){
        articleLabelService.deleteArticleLabelById(id);
        return ResultFactory.buildSuccessResult("标签删除成功");
    }
}
