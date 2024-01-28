package com.example.blog.controller;

import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.BlogDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * @author: lxk
 * @time: 2021/8/7 16:03
 * @description :不需要权限的日记相关操作
 */

@RestController
@ResponseBody
@RequestMapping("/diary")
public class BlogDiaryController {

    @Autowired
    private BlogDiaryService blogDiaryService;

    /**
     * 传入日记列表的size（页大小）与page（第几页）来查询日记
     * @param size
     * @param page
     * @return
     */
    @GetMapping("/select/list/{size}/{page}")
    public Result selectDiaryByPage(@PathVariable("size") int size, @PathVariable("page") int page) {
        return ResultFactory.buildSuccessResult(blogDiaryService.selectDiaryList(page, size));
    }

    /**
     * 通过日期查询日记
     * @param date
     * @return
     */
    @GetMapping("/select/{date}")
    public Result selectDiaryByDate(@PathVariable("date") Date date) {
        return ResultFactory.buildSuccessResult(blogDiaryService.selectDiaryByCreatTime(date));
    }

}
