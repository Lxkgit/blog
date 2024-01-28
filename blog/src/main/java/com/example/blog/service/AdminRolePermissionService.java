package com.example.blog.service;

import com.example.blog.entity.AdminPermission;
import com.example.blog.entity.AdminRolePermission;

import java.util.List;

public interface AdminRolePermissionService {
    List<AdminRolePermission> selectPermissionIdByRids(List<Integer> rids);
    List<AdminRolePermission> selectPermissionIdByRid(Integer rid);
    void updateRolePermission(int rid, List<AdminPermission> perms);
}
