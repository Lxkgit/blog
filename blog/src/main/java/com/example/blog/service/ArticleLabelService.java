package com.example.blog.service;

import com.example.blog.entity.ArticleLabel;

import java.util.List;

/**
 * @author: lxk
 * @date: 2022/1/12 14:28
 * @description: 文章标签服务类
 * @modified By:
 */
public interface ArticleLabelService {
    List<ArticleLabel> selectArticleLabelList();
    List<ArticleLabel> selectArticleLabelByLabelTypeId(Integer id);
    ArticleLabel selectArticleLabelById(Integer id);
    void insertArticleLabel(ArticleLabel articleLabel);
    void updateArticleLabel(ArticleLabel articleLabel);
    void deleteArticleLabelById(Integer id);
}
