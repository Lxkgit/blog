package com.example.blog.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: lxk
 * @time: 2021/8/30 16:57
 * @description :redis缓存数据提取类
 */

public class MyCastUtils {
    public static <T> List<T> objectConvertToList(Object object, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (object instanceof List<?>) {
            for (Object o : (List<?>) object) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }
}
