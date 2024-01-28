package com.example.blog.dao;

import com.example.blog.entity.ArticleType;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ArticleTypeDAO {
    List<ArticleType> selectArticleTypeList();
    List<ArticleType> selectArticleTypeByParentId(int parentId);
    ArticleType selectArticleTypeById(int id);
    void updateArticleType(ArticleType articleType);
    void insertArticleType(ArticleType articleType);
    void deleteArticleTypeById(int id);

}
