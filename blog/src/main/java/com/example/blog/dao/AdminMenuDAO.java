package com.example.blog.dao;

import com.example.blog.entity.AdminMenu;

import java.util.List;

public interface AdminMenuDAO {
    List<AdminMenu> selectMenuByIds(List<Integer> ids);
    List<AdminMenu> selectMenuByParentId(int id);
}
