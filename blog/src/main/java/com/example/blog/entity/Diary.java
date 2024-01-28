package com.example.blog.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

/**
 * @author: lxk
 * @time: 2021/8/7 13:56
 * @description :日记类
 */

@Data
public class Diary {

    private int id;

    /**
     * 日期格式
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_time;

    private String diary_html;
}
