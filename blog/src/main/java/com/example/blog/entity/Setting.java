package com.example.blog.entity;

import lombok.Data;

/**
 * @author: lxk
 * @time: 2021/10/28 10:34
 * @description :系统设置
 */

@Data
public class Setting {

    private int id;

    private String title;
    private String type;
    private String key;
    private String value;
}
