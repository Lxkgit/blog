package com.example.blog.dao;


import com.example.blog.entity.AdminUserRole;

import java.util.List;

public interface AdminUserRoleDAO {
    List<AdminUserRole> selectRidByUid(int uid);
}
