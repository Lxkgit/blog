package com.example.blog.service;

import com.example.blog.entity.Setting;
import com.example.blog.entity.VO.SettingVo;

import java.util.List;

public interface SettingService {
    List<SettingVo> selectSettingList();
    SettingVo selectSettingByKey(String key);
    String updateValueByKey(SettingVo settingVo);
}
