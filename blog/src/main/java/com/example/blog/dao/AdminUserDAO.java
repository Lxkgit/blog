package com.example.blog.dao;

import com.example.blog.entity.BlogUser;

import java.util.List;

public interface AdminUserDAO {
    /**
     * 查询用户列表
     * @return
     */
    List<BlogUser> selectUserList();
    BlogUser selectUserByEmail(String email);
    void updateUserPassword(BlogUser user);

    /**
     * 修改用户账号状态
     * @param user
     */
    void updateUserStatusById(BlogUser user);
    void updateUserMsg(BlogUser user);
}
