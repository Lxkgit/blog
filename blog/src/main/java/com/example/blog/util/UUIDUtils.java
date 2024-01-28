package com.example.blog.util;

import java.util.UUID;

/**
 * @author: lxk
 * @date: 2021/7/25 18:16
 * @description: 邮件发送类
 * @modified By:
 */
public class UUIDUtils {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
