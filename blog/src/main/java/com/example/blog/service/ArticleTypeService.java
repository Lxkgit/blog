package com.example.blog.service;

import com.example.blog.entity.AdminMenu;
import com.example.blog.entity.ArticleType;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ArticleTypeService {
    List<ArticleType> selectArticleTypeList();
    List<ArticleType> selectArticleTypeTree();
    ArticleType selectArticleTypeById(int id);
    PageInfo<ArticleType> selectArticleTypeListPage(int pageNum, int pageSize);
    void updateArticleType(ArticleType articleType);
    void insertArticleType(ArticleType articleType);
    void deleteArticleTypeById(int id);
    List<ArticleType> selectArticleTypeByParentId(int parentId);
    void handleMenus(List<ArticleType> types);
}
