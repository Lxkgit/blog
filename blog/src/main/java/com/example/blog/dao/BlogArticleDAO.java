package com.example.blog.dao;

import com.example.blog.entity.Article;

import java.util.List;


public interface BlogArticleDAO {
    List<Article> selectArticleList(int startNum, int pageSize);
    Integer selectArticleCount();
    Article selectArticleById(int id);
    void updateArticle(Article article);
    void insertArticle(Article article);
    void deleteArticleById(int id);
}
