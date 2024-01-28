package com.example.blog.entity;

import lombok.Data;

/**
 * @author: 308501
 * @time: 2021/11/30 16:33
 * @description :游戏列表数据
 */

@Data
public class GameList {

    private int id;

    private String gameName;
    private String gameType;
    private String gameSchedule;
    private String gameImg;
}
