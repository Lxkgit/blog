package com.example.blog.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

/**
 * @author: lxk
 * @time: 2021/9/24 9:12
 * @description :博客更新日志
 */
@Data
public class Update {
    private int id;

    /**
     * 日期格式
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    private String updateHtml;
}
