package com.example.blog.dao;

import com.example.blog.entity.AdminRole;

import java.util.List;

public interface AdminRoleDAO {
    List<AdminRole> selectRoleList();
    void updateRole(AdminRole adminRole);
    void insertRole(AdminRole adminRole);
    void deleteRoleById(Integer id);
}
