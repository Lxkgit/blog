package com.example.blog.controller;

import com.example.blog.entity.Diary;
import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.BlogDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

/**
 * @author: lxk
 * @time: 2021/8/7 16:02
 * @description :需要权限的日记相关操作
 */

@RestController
@ResponseBody
@RequestMapping("/admin/diary")
public class AdminDiaryController {

    @Autowired
    private BlogDiaryService blogDiaryService;

    /**
     * 新增日记接口
     * @param diary
     * @return
     */
    @PostMapping("/insert")
    public Result insertDiary(@RequestBody Diary diary) {
        blogDiaryService.insertDiary(diary);
        return ResultFactory.buildSuccessResult("新增日记成功");
    }

    /**
     * 删除日记接口
     * @param id
     * @return
     */
    @DeleteMapping("/delete/id/{id}")
    public Result deleteDiary(@PathVariable("id") int id) {
        blogDiaryService.deleteDiaryById(id);
        return ResultFactory.buildSuccessResult("删除日记成功");
    }

    /**
     * 修改日记接口
     * @param diary
     * @return
     */
    @PostMapping("/update")
    public Result updateDiary(@RequestBody Diary diary) {
        blogDiaryService.updateDiaryById(diary);
        return ResultFactory.buildSuccessResult("修改日记成功");
    }

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
