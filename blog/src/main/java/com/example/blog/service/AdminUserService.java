package com.example.blog.service;

import com.example.blog.entity.BlogUser;

import java.util.List;

public interface AdminUserService {
    /**
     * 返回用户列表
     * @return
     */
    List<BlogUser> selectUserList();
    void updateUserStatusById(BlogUser user);
    void updateUserPassword(BlogUser user);
    void updateUserMsg(BlogUser user);
}
