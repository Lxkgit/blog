package com.example.blog.controller;

import com.example.blog.entity.Setting;
import com.example.blog.entity.VO.SettingVo;
import com.example.blog.result.Result;
import com.example.blog.result.ResultFactory;
import com.example.blog.service.SettingService;
import com.example.blog.util.FileUtil;
import com.example.blog.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * @author: lxk
 * @time: 2021/11/3 18:39
 * @description :文件上传
 */

@RestController
@ResponseBody
@RequestMapping("/admin/file")
public class FileController {

    @Autowired
    private SettingService settingService;

    @PostMapping(value = "/upload/img")
    public Result imgUpload(@RequestParam(value = "file")MultipartFile file, SettingVo setting) {
        String readFilePath = "file:///D:/workspace/img/";
        String filePath = "D:/workspace/img/";
        String fileName = file.getOriginalFilename();
        String suffixName = null;
        assert fileName != null;
        suffixName = fileName.substring(fileName.lastIndexOf("."));
        String prefixName = FileUtil.getRandomString(6);
        String newFileName = prefixName + suffixName;
        String oldFileName = setting.getValue().substring(setting.getValue().lastIndexOf("/") + 1);
        setting.setValue(readFilePath + newFileName);
        System.out.println(newFileName);
        settingService.updateValueByKey(setting);
        try {
            FileUtil.deleteFile(filePath, oldFileName);
            FileUtil.uploadFile(file.getBytes(), filePath, newFileName);
            return ResultFactory.buildSuccessResult("上传成功");
        } catch (Exception e) {
            return ResultFactory.buildFailResult("上传失败");
        }

    }
}
