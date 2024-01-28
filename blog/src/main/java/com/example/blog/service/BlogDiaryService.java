package com.example.blog.service;

import com.example.blog.entity.Diary;
import com.github.pagehelper.PageInfo;

import java.sql.Date;
import java.util.List;

public interface BlogDiaryService {
    PageInfo<Diary> selectDiaryList(int pageNum, int pageSize);

    Diary selectDiaryByCreatTime(Date date);

    void updateDiaryById(Diary diary);

    void insertDiary(Diary diary);

    void deleteDiaryById(Integer id);
}
