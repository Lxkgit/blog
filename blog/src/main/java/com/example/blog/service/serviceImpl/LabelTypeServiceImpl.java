package com.example.blog.service.serviceImpl;

import com.example.blog.dao.ArticleLabelDAO;
import com.example.blog.dao.LabelTypeDAO;
import com.example.blog.entity.ArticleLabel;
import com.example.blog.entity.LabelType;
import com.example.blog.service.LabelTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: lxk
 * @date: 2022/1/13 14:00
 * @description: 标签分类服务
 * @modified By:
 */
@Service
public class LabelTypeServiceImpl implements LabelTypeService {

    @Resource
    private LabelTypeDAO labelTypeDAO;

    @Resource
    private ArticleLabelDAO articleLabelDAO;

    @Override
    public List<LabelType> selectLabelTypeList() {
        return labelTypeDAO.selectLabelTypeList();
    }

    @Override
    public List<LabelType> selectLabelTypeAndLabelList() {
        List<LabelType> labelTypeList = labelTypeDAO.selectLabelTypeList();
        for (LabelType labelType : labelTypeList){
            List<ArticleLabel> articleLabelList = articleLabelDAO.selectArticleLabelByLabelType(labelType.getLabelTypeId());
            labelType.setArticleLabelList(articleLabelList);
        }
        return labelTypeList;
    }

    @Override
    public LabelType selectLabelTypeById(Integer id) {
        return labelTypeDAO.selectLabelTypeById(id);
    }

    @Override
    public LabelType selectLabelTypeAndLabelById(Integer id) {
        return labelTypeDAO.selectLabelTypeAndLabelById(id);
    }

    @Override
    public void insertLabelType(LabelType labelType) {
        labelTypeDAO.insertLabelType(labelType);
    }

    @Override
    public void updateLabelType(LabelType labelType) {
        labelTypeDAO.updateLabelType(labelType);
    }

    @Override
    public void deleteLabelTypeById(Integer id) {
        labelTypeDAO.deleteLabelTypeById(id);
    }
}
