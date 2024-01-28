package com.example.blog.dao;

import com.example.blog.entity.Update;

import java.util.List;

public interface BlogUpdateDAO {
    List<Update> selectUpdateList();
    void updateUpdateById(Update update);
    void insertUpdate(Update update);
    void deleteUpdateById(Integer id);
}
