package com.example.blog.entity;

import lombok.Data;

/**
 * @author: lxk
 * @date: 2021/7/4 11:21
 * @description: 管理员权限
 * @modified By:
 */
@Data
public class AdminPermission {
    private int id;
    private String name;
    private String desc_;
    private String url;
}
