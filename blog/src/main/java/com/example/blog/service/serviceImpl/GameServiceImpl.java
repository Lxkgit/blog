package com.example.blog.service.serviceImpl;

import com.alibaba.druid.util.StringUtils;
import com.example.blog.entity.GameList;
import com.example.blog.service.GameService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 308501
 * @time: 2021/11/30 16:34
 * @description :游戏相关服务类，包括游戏图片存档数据库
 */

@Service
public class GameServiceImpl implements GameService {

    @Override
    public List<GameList> selectAllGame() {

        return null;
    }
}
