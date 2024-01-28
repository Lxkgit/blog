package com.example.blog.controller;

import com.example.blog.entity.Update;
import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.BlogUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lxk
 * @time: 2021/9/24 9:18
 * @description :管理员更新博客使用
 */

@RestController
@ResponseBody
@RequestMapping("/admin/update")
public class AdminUpdateController {

    @Autowired
    private BlogUpdateService blogUpdateService;

    /**
     * 管理员分页查找系统更新日志
     * 此方法有重复，但是为了在前端页面接口统一所以重复编写
     * @param size
     * @param page
     * @return
     */
    @GetMapping("/select/list/{size}/{page}")
    public Result selectUpdateByPage(@PathVariable("size")int size, @PathVariable("page")int page){
        return ResultFactory.buildSuccessResult(blogUpdateService.selectUpdateList(page ,size));
    }

    /**
     * 管理员修改系统更新日志
     * @param update
     * @return
     */
    @PostMapping("/update")
    public Result updateUpdate(@RequestBody Update update){
        blogUpdateService.updateUpdateById(update);
        return ResultFactory.buildSuccessResult("系统更新日志修改完成");
    }

    /**
     * 管理员上传系统更新日志
     * @param update
     * @return
     */
    @PostMapping("/insert")
    public Result insertUpdate(@RequestBody Update update) {
        blogUpdateService.insertUpdate(update);
        return ResultFactory.buildSuccessResult("系统更新日志上传成功");
    }

    /**
     * 管理员删除系统更新日志
     * @param id
     * @return
     */
    @DeleteMapping("/delete/id/{id}")
    public Result deleteUpdateById(@PathVariable("id") int id) {
        blogUpdateService.deleteUpdateById(id);
        return ResultFactory.buildSuccessResult("系统更新日志删除完成");
    }

}
