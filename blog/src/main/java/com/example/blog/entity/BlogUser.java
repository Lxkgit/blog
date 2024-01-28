package com.example.blog.entity;

import lombok.Data;

import java.security.SecureRandom;

/**
 * @author: lxk
 * @date: 2021/6/28 10:45
 * @description: 用户账号类
 * @modified By:
 */
@Data
public class BlogUser {

    private Integer id;

    private String username;

    private String email;

    private String password;

    private String salt;

    private int status;

    private String code;

}
