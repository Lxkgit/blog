package com.example.blog.service;

import com.example.blog.entity.Article;
import com.example.blog.util.MyPage;
import com.github.pagehelper.PageInfo;

public interface BlogArticleService {
    void insertArticle(Article article);
    void updateArticle(Article article);
    MyPage<Article> selectArticleList(int page, int size);
    Article selectArticleById(int id);
    void deleteArticleById(int id);
}
