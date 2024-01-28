package com.example.blog.entity;

import lombok.Data;

/**
 * @author: 308501
 * @time: 2021/11/15 14:01
 * @description :文档内容
 */

@Data
public class DocContent {
    private int id;

    private String title;

    private String content_md;

    private String content_html;
}
