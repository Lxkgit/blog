package com.example.blog.service.serviceImpl;

import com.example.blog.dao.SettingDAO;
import com.example.blog.entity.Setting;
import com.example.blog.entity.VO.SettingVo;
import com.example.blog.service.SettingService;
import com.example.blog.util.PropertiesUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lxk
 * @time: 2021/10/28 10:36
 * @description :系统设置服务类
 */

@Service
public class SettingServiceImpl implements SettingService {

    @Resource
    private SettingDAO settingDAO;

    @Override
    public List<SettingVo> selectSettingList() {
        List<SettingVo> settingsVo = new ArrayList<>();
        List<Setting> settings;
        settings = settingDAO.selectSettingList();
        for (Setting setting : settings) {
            SettingVo settingVo = new SettingVo();
            settingVo.setId(setting.getId());
            settingVo.setKey(setting.getKey());
            settingVo.setTitle(setting.getTitle());
            settingVo.setType(setting.getType());
            if (setting.getType().equals("number")) {
                settingVo.setNum(Integer.parseInt(setting.getValue()));
            } else if (setting.getType().equals("switch")) {
                if (setting.getValue().equals("true")){
                    settingVo.setBool(true);
                } else if (setting.getValue().equals("false")) {
                    settingVo.setBool(false);
                }
            } else {
                settingVo.setValue(setting.getValue());
            }
            settingsVo.add(settingVo);
        }
        return settingsVo;
    }

    @Override
    public SettingVo selectSettingByKey(String key) {
        SettingVo settingVo = new SettingVo();
        Setting setting = settingDAO.selectSettingByKey(key);
        settingVo.setKey(setting.getKey());
        settingVo.setValue(setting.getValue());
        return settingVo;
    }

    @Override
    public String updateValueByKey(SettingVo settingVo) {
        Setting setting = new Setting();
        setting.setKey(settingVo.getKey());
        setting.setValue(settingVo.getValue());
        if (setting.getValue() == null || setting.getValue().equals("")) {
            setting.setValue("null");
        }
        if (setting.getKey().startsWith("spring")) {
            Map<String, String> map = new HashMap<>();
            map.put(setting.getKey(),setting.getValue());
            PropertiesUtil.updateProperties(map);
        }
        settingDAO.updateValueByKey(setting);
        return "1";
    }

}
