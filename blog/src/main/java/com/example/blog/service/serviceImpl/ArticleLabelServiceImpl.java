package com.example.blog.service.serviceImpl;

import com.example.blog.dao.ArticleLabelDAO;
import com.example.blog.entity.ArticleLabel;
import com.example.blog.service.ArticleLabelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: lxk
 * @date: 2022/1/12 14:30
 * @description: 文章标签服务类实现
 * @modified By:
 */
@Service
public class ArticleLabelServiceImpl implements ArticleLabelService {

    @Resource
    private ArticleLabelDAO articleLabelDAO;

    @Override
    public List<ArticleLabel> selectArticleLabelList() {
        return articleLabelDAO.selectArticleLabelList();
    }

    @Override
    public List<ArticleLabel> selectArticleLabelByLabelTypeId(Integer id) {
        return articleLabelDAO.selectArticleLabelByLabelType(id);
    }

    @Override
    public ArticleLabel selectArticleLabelById(Integer id) {
        return articleLabelDAO.selectArticleLabelById(id);
    }

    @Override
    public void insertArticleLabel(ArticleLabel articleLabel) {
        articleLabelDAO.insertArticleLabel(articleLabel);
    }

    @Override
    public void updateArticleLabel(ArticleLabel articleLabel) {
        articleLabelDAO.updateArticleLabel(articleLabel);
    }

    @Override
    public void deleteArticleLabelById(Integer id) {
        articleLabelDAO.deleteArticleLabelById(id);
    }
}
