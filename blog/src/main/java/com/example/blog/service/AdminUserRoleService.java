package com.example.blog.service;

import com.example.blog.entity.AdminRoleMenu;
import com.example.blog.entity.AdminUserRole;

import java.util.List;

public interface AdminUserRoleService {
    List<AdminUserRole> selectRidByUid(int uid);
}
