package com.example.blog.controller;

import com.example.blog.entity.LabelType;
import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.LabelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lxk
 * @date: 2022/1/13 14:03
 * @description: 标签分类接口
 * @modified By:
 */
@RestController
@ResponseBody
@RequestMapping("/admin/label/type")
public class AdminLabelTypeController {

    @Autowired
    private LabelTypeService labelTypeService;

    @GetMapping("/select/list")
    public Result selectArticleLabelTypeList(){
        return ResultFactory.buildSuccessResult(labelTypeService.selectLabelTypeList());
    }

    @GetMapping("/select/label/list")
    public Result selectArticleLabelTypeAndLabelList(){
        return ResultFactory.buildSuccessResult(labelTypeService.selectLabelTypeAndLabelList());
    }

    @GetMapping("/select/{id}")
    public Result selectArticleLabelTypeById(@PathVariable("id") Integer id){
        return ResultFactory.buildSuccessResult(labelTypeService.selectLabelTypeById(id));

    }

    @GetMapping("/select/label/{id}")
    public Result selectArticleLabelTypeAndLabelById(@PathVariable("id") Integer id){
        return ResultFactory.buildSuccessResult(labelTypeService.selectLabelTypeAndLabelById(id));
    }

    @PostMapping("/insert")
    public Result insertArticleLabelType(@RequestBody LabelType labelType){
        labelTypeService.insertLabelType(labelType);
        return ResultFactory.buildSuccessResult("新增标签类");
    }

    @PostMapping("/update")
    public Result updateArticleLabelType(@RequestBody LabelType labelType){
        labelTypeService.updateLabelType(labelType);
        return ResultFactory.buildSuccessResult("标签类修改成功");
    }

    @GetMapping("/delete/{id}")
    public Result deleteArticleLabelTypeById(@PathVariable("id") Integer id){
        labelTypeService.deleteLabelTypeById(id);
        return ResultFactory.buildSuccessResult("删除标签类成功");
    }
}
