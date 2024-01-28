package com.example.blog.controller;

import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.BlogUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lxk
 * @time: 2021/9/24 9:20
 * @description :博客更新内容展示
 */

@RestController
@ResponseBody
@RequestMapping("/update")
public class BlogUpdateController {

    @Autowired
    private BlogUpdateService blogUpdateService;

    @GetMapping("/select/list/{size}/{page}")
    public Result selectUpdateByPage(@PathVariable("size")int size,@PathVariable("page")int page){
        return ResultFactory.buildSuccessResult(blogUpdateService.selectUpdateList(page ,size));
    }
}
