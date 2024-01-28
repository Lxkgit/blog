package com.example.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author: lxk
 * @date: 2022/1/13 11:14
 * @description: 文章标签类型分组
 * @modified By:
 */
@Data
@JsonIgnoreProperties(value = { "handler" })
public class LabelType {
    private int labelTypeId;
    private String labelTypeName;
    private int labelNum;
    private List<ArticleLabel> articleLabelList;
}
