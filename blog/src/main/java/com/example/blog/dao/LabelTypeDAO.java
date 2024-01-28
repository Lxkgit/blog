package com.example.blog.dao;

import com.example.blog.entity.LabelType;

import java.util.List;

public interface LabelTypeDAO {
    List<LabelType> selectLabelTypeList();
    List<LabelType> selectLabelTypeAndLabelList();
    LabelType selectLabelTypeById(Integer id);
    LabelType selectLabelTypeAndLabelById(Integer id);
    void insertLabelType(LabelType labelType);
    void updateLabelType(LabelType labelType);
    void deleteLabelTypeById(Integer id);
}
