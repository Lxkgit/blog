package com.example.blog.dao;

import com.example.blog.entity.AdminRoleMenu;

import java.util.List;

public interface AdminRoleMenuDAO {
    List<AdminRoleMenu> selectMenuIdByRid(Integer rid);
    List<AdminRoleMenu> selectMenuIdByRids(List<Integer> rids);
    void deleteRoleMenuByRid(Integer rid);
    void insertRoleMenu(List<AdminRoleMenu> adminRoleMenus);
}
