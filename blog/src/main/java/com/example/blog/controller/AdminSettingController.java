package com.example.blog.controller;

import com.example.blog.entity.BlogUser;
import com.example.blog.entity.Setting;
import com.example.blog.entity.VO.SettingVo;
import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author: 308501
 * @time: 2021/11/15 10:43
 * @description :管理员权限的设置功能
 */

@RestController
@ResponseBody
@RequestMapping("/admin/setting")
public class AdminSettingController {

    @Autowired
    private SettingService settingService;

    @GetMapping("/select/list")
    public Result selectSettingList() {
        return ResultFactory.buildSuccessResult(settingService.selectSettingList());
    }

    @PostMapping("/update/value")
    public Result updateValueByKey(@RequestBody SettingVo settingVo) {
        return ResultFactory.buildSuccessResult(settingService.updateValueByKey(settingVo));
    }
}
