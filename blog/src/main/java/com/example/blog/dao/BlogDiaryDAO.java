package com.example.blog.dao;

import com.example.blog.entity.Diary;

import java.sql.Date;
import java.util.List;

public interface BlogDiaryDAO {
    List<Diary> selectDiaryList();
    Diary selectDiaryByCreatTime(Date date);
    void updateDiaryById(Diary diary);
    void insertDiary(Diary diary);
    void deleteDiaryById(Integer id);
}
