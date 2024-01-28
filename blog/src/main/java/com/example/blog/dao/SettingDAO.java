package com.example.blog.dao;

import com.example.blog.entity.Setting;

import java.util.List;

/**
 * @author: lxk
 * @time: 2021/10/28 10:36
 * @description :系统设置接口类
 */

public interface SettingDAO {
    List<Setting> selectSettingList();
    Setting selectSettingByKey(String key);
    void updateValueByKey(Setting setting);
}
