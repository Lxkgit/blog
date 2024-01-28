package com.example.blog.service;

import com.example.blog.entity.AdminRoleMenu;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AdminRoleMenuService {
    List<AdminRoleMenu> selectMenuIdByRid(Integer rid);
    List<AdminRoleMenu> selectMenuIdByRids(List<Integer> rids);
    void updateRoleMenu(int rid, Map<String, List<Integer>> menusIds);

}
