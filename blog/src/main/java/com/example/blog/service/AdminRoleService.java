package com.example.blog.service;

import com.example.blog.entity.AdminRole;

import java.util.List;

public interface AdminRoleService {
    List<AdminRole> selectPermsAndMenus();
    void updateRoleStatus(AdminRole adminRole);
    void updateRole(AdminRole adminRole);
    void insertRole(AdminRole adminRole);
    void deleteRoleById(Integer id);


}
