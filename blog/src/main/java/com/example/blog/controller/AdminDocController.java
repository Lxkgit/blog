package com.example.blog.controller;

import com.example.blog.entity.DocCatalog;
import com.example.blog.entity.DocContent;
import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.DocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 308501
 * @time: 2021/11/15 14:24
 * @description :有权限Doc控制类
 */

@RestController
@ResponseBody
@RequestMapping("/admin/doc")
public class AdminDocController {

    @Autowired
    private DocService docService;

    @GetMapping("/select/list")
    public Result selectDocList() {
        return ResultFactory.buildSuccessResult(docService.selectDocCatalogList());
    }

    @GetMapping("/select/content/{id}")
    public Result selectDocContentById(@PathVariable("id") Integer id) {
        return ResultFactory.buildSuccessResult(docService.selectDocContentById(id));
    }

    @PostMapping("/insert/doc")
    public Result insertDoc(@RequestBody DocCatalog docCatalog) {
        docService.insertDoc(docCatalog);
        return ResultFactory.buildSuccessResult("新增文档成功");
    }

    @DeleteMapping("/delete/doc/{id}")
    public Result deleteDoc(@PathVariable("id") int id){
        String msg = docService.deleteDoc(id);
        return ResultFactory.buildSuccessResult(msg);
    }

    @PostMapping("/update/catalog")
    public Result updateDocCatalog(@RequestBody DocCatalog docCatalog) {
        docService.updateDocCatalog(docCatalog);
        return ResultFactory.buildSuccessResult("文档列表修改成功");
    }

    @PostMapping("/update/content")
    public Result updateDocContent(@RequestBody DocContent docContent) {
        docService.updateDocContent(docContent);
        return ResultFactory.buildSuccessResult("文档内容修改成功");
    }
}
