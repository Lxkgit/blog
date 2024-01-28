package com.example.blog.service;

import com.example.blog.entity.Update;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BlogUpdateService {
    PageInfo<Update> selectUpdateList(int pageNum, int pageSize);
    void updateUpdateById(Update update);
    void insertUpdate(Update update);
    void deleteUpdateById(Integer id);
}
