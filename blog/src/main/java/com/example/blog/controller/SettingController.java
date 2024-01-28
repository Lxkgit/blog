package com.example.blog.controller;

import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.SettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author: lxk
 * @time: 2021/10/28 10:48
 * @description :系统设置控制类
 */

@RestController
@ResponseBody
@RequestMapping("/setting")
public class SettingController {

    private final Logger logger = LoggerFactory.getLogger(SettingController.class);

    @Autowired
    private SettingService settingService;

    @GetMapping("/select/{key}")
    public Result selectSettingByKey(@PathVariable("key") String key) {
        logger.info("/select/{key} 接口被访问" );
        return ResultFactory.buildSuccessResult(settingService.selectSettingByKey(key));
    }
}
