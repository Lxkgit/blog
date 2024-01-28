package com.example.blog.entity;

import lombok.Data;

/**
 * @author: lxk
 * @date: 2021/7/4 11:22
 * @description: 管理员角色权限表
 * @modified By:
 */
@Data
public class AdminRolePermission {
    private int id;
    private int rid;
    private int pid;
}
