package com.example.blog.dao;

import com.example.blog.entity.AdminPermission;

import java.util.List;

public interface AdminPermissionDAO {
    List<AdminPermission> selectPermissionByIds(List<Integer> id);
    List<AdminPermission> selectPermissionList();
    List<AdminPermission> findPermsByRoleId(int id);
}
