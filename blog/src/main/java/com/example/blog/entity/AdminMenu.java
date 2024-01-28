package com.example.blog.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: lxk
 * @date: 2021/6/30 19:40
 * @description: 后台菜单表
 * @modified By:
 */
@Data
public class AdminMenu {

    private int id;

    private String path;

    private String name;

    private String nameZh;

    private String iconCls;

    private String component;

    private int parentId;

    private List<AdminMenu> children;
}
