package com.example.blog.controller;

import com.example.blog.entity.ArticleType;
import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.ArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lxk
 * @time: 2021/8/3 11:32
 * @description :博客标签类
 */

@RestController
@ResponseBody
@RequestMapping("/admin/label")
public class AdminArticleTypeController {

    @Autowired
    ArticleTypeService articleTypeService;

    /**
     * 保存文章标签
     * @param articleType
     * @return
     */
    @PostMapping("/insert")
    public Result insertArticleType(@RequestBody ArticleType articleType) {
        articleTypeService.insertArticleType(articleType);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    /**
     * 查询文章类型列表
     * @return
     */
    @GetMapping("/select/list")
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
     * 分页查找文章标签
     * @param size
     * @param page
     * @return
     */
    @GetMapping("/select/list/{size}/{page}")
    public Result selectArticleTypeList(@PathVariable("size") int size, @PathVariable("page") int page){
        return ResultFactory.buildSuccessResult(articleTypeService.selectArticleTypeListPage(page, size));
    }

    /**
     * 修改文章标签
     * @param articleType
     * @return
     */
    @PostMapping("/update")
    public Result updateArticleType(@RequestBody ArticleType articleType) {
        articleTypeService.updateArticleType(articleType);
        return ResultFactory.buildSuccessResult("修改成功");
    }

    /**
     * 通过id删除文章标签
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteArticleTypeById(@PathVariable("id") int id) {
        articleTypeService.deleteArticleTypeById(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
}
