package com.example.blog.service;

import com.example.blog.entity.GameList;

import java.util.List;

public interface GameService {
    List<GameList> selectAllGame();
}
