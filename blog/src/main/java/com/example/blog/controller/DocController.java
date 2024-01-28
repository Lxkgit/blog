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
 * @time: 2021/11/15 14:23
 * @description :无权限doc控制类
 */

@RestController
@ResponseBody
@RequestMapping("/doc")
public class DocController {

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
}
