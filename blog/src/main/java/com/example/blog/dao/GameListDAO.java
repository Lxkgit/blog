package com.example.blog.dao;

import com.example.blog.entity.GameList;

import java.util.List;

public interface GameListDAO {

    List<GameList> selectGameListAll();
}
