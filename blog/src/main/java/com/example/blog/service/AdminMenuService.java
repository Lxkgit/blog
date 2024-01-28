package com.example.blog.service;

import com.example.blog.entity.AdminMenu;

import java.util.List;

public interface AdminMenuService {
    public List<AdminMenu> selectMenusByCurrentUser();
    public List<AdminMenu> selectMenuByParentId(int parentId);
    public void handleMenus(List<AdminMenu> menus);
    public List<AdminMenu> selectMenuByRoleId(int rid);
}
