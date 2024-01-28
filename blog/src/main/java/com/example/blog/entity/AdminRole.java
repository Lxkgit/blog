package com.example.blog.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: lxk
 * @date: 2021/6/30 19:39
 * @description: 用户角色表
 * @modified By:
 */
@Data
public class AdminRole {

    private int id;

    private String name;

    private String nameZh;

    private int status;

    private List<AdminPermission> perms;

    private List<AdminMenu> menus;
}
