package com.example.blog.dao;

import com.example.blog.entity.BlogUser;

public interface LoginDAO {
    BlogUser selectUserByEmail(String email);
    int insertUser(BlogUser user);
    BlogUser selectUserByCode(String code);
    void updateUserStatus(BlogUser user);
}
