package com.example.blog.dao;

import com.example.blog.entity.AdminRolePermission;

import java.util.List;

public interface AdminRolePermissionDAO {
    List<AdminRolePermission> selectPermissionIdByRids(List<Integer> rids);
    List<AdminRolePermission> selectPermissionIdByRid(Integer rid);
    void deletePermissionByRid(int rid);
    void insertRolePermission(List<AdminRolePermission> adminRolePermissions);
}
