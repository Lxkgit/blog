package com.example.blog.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.List;

/**
 * @author: lxk
 * @date: 2021/7/5 13:29
 * @description: 博客文章
 * @modified By:
 */
@Data
public class Article {

    private int id;

//    private int uid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_time;

    private String title;

    private int browse_count;

    private int like_count;

    private String content_md;

    private String content_html;

    private String articleType;

    private String articleLabel;

    private List<ArticleLabel> labels;

    private List<ArticleType> types;
}
