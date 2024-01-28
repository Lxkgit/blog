package com.example.blog.util;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author: 308501
 * @time: 2021/12/15 19:39
 * @description :修改properties配置文件工具
 */

public class PropertiesUtil {

    public static final Properties p = new Properties();
    public static final String path = "application-test.properties";

    /**
     * @Title: getProfileByPropertiesLoaderUtils
     * @Description: Spring 提供的 PropertiesLoaderUtils 允许您直接通过基于类路径的文件地址加载属性资源
     * 最大的好处就是：实时加载配置文件，修改后立即生效，不必重启
     * @return Map<String,Object>
     */
    public static Map<String, String> getProfileByPropertiesLoaderUtils() {
        Properties props;
        Map<String, String> profileMap = new HashMap<>();
        try {
            props = PropertiesLoaderUtils.loadAllProperties(path);
            for (Object key : props.keySet()) {
                profileMap.put(key.toString(), props.getProperty(key.toString()));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return profileMap;
    }

    /**
     * 传递键值对的Map，更新properties文件
     * @param keyValueMap 键值对Map
     */
    public static void updateProperties(Map<String, String> keyValueMap) {
        String filePath = "src\\main\\resources\\application-test.properties";
        Properties props = new Properties();
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            // 从输入流中读取属性列表（键和元素对）
            br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            props.load(br);
            br.close();
            // 写入属性文件
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
            // 清空旧的文件
            // props.clear();
            for (String key : keyValueMap.keySet()){
                System.out.println(key+ "  " + keyValueMap.get(key));
                props.setProperty(key, keyValueMap.get(key));
            }
            props.store(bw, "Configuration information");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
