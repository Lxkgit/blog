package com.example.blog.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author: lxk
 * @date: 2021/7/18 10:10
 * @description: 图片上传类
 * @modified By:
 */
public class Img {

    static public String imgUpload(String name, MultipartFile file) {
        String folder = "F:/img/test";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, "test" + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));

        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
