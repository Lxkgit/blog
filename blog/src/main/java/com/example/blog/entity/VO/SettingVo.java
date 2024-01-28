package com.example.blog.entity.VO;

import lombok.Data;

/**
 * @author: lxk
 * @date: 2021/10/30 20:32
 * @description: 设置类-面向页面
 * @modified By:
 */
@Data
public class SettingVo {

    private int id;

    private String title;
    private String type;
    private String key;
    private String value;

    private int num;
    private boolean bool;
}
