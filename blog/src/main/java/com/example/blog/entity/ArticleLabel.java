package com.example.blog.entity;

import lombok.Data;

/**
 * @author: lxk
 * @date: 2022/1/12 10:45
 * @description: 文章标签
 * @modified By:
 */
@Data
public class ArticleLabel {
    private int labelId;
    private String labelName;
    private int labelTypeId;
    private int articleNum;
}
