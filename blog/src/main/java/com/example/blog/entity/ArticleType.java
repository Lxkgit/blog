package com.example.blog.entity;

import lombok.Data;

import java.util.List;

/**
 * @author: lxk
 * @date: 2021/7/28 19:21
 * @description: 博客文章类型
 * @modified By:
 */
@Data
public class ArticleType {
    private int value;
    private String label;
    private int parent;
    private int num;
    private List<ArticleType> children;
}
