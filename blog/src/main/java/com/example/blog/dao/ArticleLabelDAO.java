package com.example.blog.dao;

import com.example.blog.entity.ArticleLabel;

import java.util.List;

public interface ArticleLabelDAO {
    List<ArticleLabel> selectArticleLabelList();
    ArticleLabel selectArticleLabelById(Integer id);
    List<ArticleLabel> selectArticleLabelByLabelType(Integer id);
    void insertArticleLabel(ArticleLabel articleLabel);
    void updateArticleLabel(ArticleLabel articleLabel);
    void deleteArticleLabelById(Integer id);
}
