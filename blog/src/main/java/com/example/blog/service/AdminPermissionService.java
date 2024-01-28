package com.example.blog.service;

import com.example.blog.entity.AdminMenu;
import com.example.blog.entity.AdminPermission;

import java.util.List;
import java.util.Set;

public interface AdminPermissionService {
    List<AdminPermission> selectPermissionList();
    Set<String> selectPermissionURLsByEmail(String email);
    boolean needFilter(String requestAPI);
    List<AdminPermission> selectPermissionByRid(int id);
}
