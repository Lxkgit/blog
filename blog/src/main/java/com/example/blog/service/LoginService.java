package com.example.blog.service;

import com.example.blog.entity.BlogUser;

import javax.lang.model.element.NestingKind;

public interface LoginService {
    BlogUser selectUserByEmail(String email);
    int insertUser(BlogUser user);
    BlogUser checkCode(String code);
    void updateUserStatus(BlogUser user);

}
