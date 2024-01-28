package com.example.blog.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author lxk
 * @date 2019/11
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        com.example.blog.util.SpringContextUtils.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}