package com.example.blog.service.serviceImpl;

import com.example.blog.dao.BlogUpdateDAO;
import com.example.blog.entity.Update;
import com.example.blog.redis.RedisService;
import com.example.blog.service.BlogUpdateService;
import com.example.blog.util.MyCastUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author: lxk
 * @time: 2021/9/24 9:24
 * @description :博客更新内容服务
 */
@Service
public class BlogUpdateServiceImpl implements BlogUpdateService {

    @Autowired
    private RedisService redisService;
    @Resource
    private BlogUpdateDAO blogUpdateDAO;

    /**
     * 分页查询系统更新
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Update> selectUpdateList(int pageNum, int pageSize) {
        // key
        String key = "updateList";
        // 从redis中获取key
        Object updateCache = redisService.get(key);
        List<Update> updates;
        // 没有获取到key，从数据库中查找
        if (updateCache == null) {
            // 数据分页
            PageHelper.startPage(pageNum, pageSize);
            updates = blogUpdateDAO.selectUpdateList();
            // 数据放入redis
            redisService.set(key, updates);
        } else {
            // 获取到key，整理格式使用
            updates = MyCastUtils.objectConvertToList(updateCache, Update.class);
        }
        // 排序
        Collections.reverse(updates);
        PageInfo<Update> pageInfo = new PageInfo<>(updates, pageSize);
        return pageInfo;
    }

    /**
     * 修改系统更新说明
     * @param update
     */
    @Override
    public void updateUpdateById(Update update) {
        // 更新数据库
        blogUpdateDAO.updateUpdateById(update);
        String key = "updateList";
        // 查找缓存
        Object updateCache = redisService.get(key);
        if (updateCache != null) {
            // 整理格式
            List<Update> updates = MyCastUtils.objectConvertToList(updateCache, Update.class);
            // 更新缓存中发生修改的数据
            for (Update u : updates) {
                if (u.getId() == update.getId()) {
                    updates.remove(u);
                    updates.add(update);
                    // 完成修改，新数据存入缓存
                    redisService.set(key, updates);
                    return;
                }
            }
        }
    }

    /**
     * 新增系统更新
     * @param update
     */
    @Override
    public void insertUpdate(Update update) {
        // 修改数据库
        blogUpdateDAO.insertUpdate(update);
        String key = "updateList";
        Object updateCache = redisService.get(key);
        if (updateCache != null) {
            List<Update> updates = MyCastUtils.objectConvertToList(updateCache, Update.class);
            updates.add(update);
            // 新数据加入缓存
            redisService.set(key, updates);
        }

    }

    /**
     * 通过id删除更新日志
     * @param id
     */
    @Override
    public void deleteUpdateById(Integer id) {
        // 更新数据库
        blogUpdateDAO.deleteUpdateById(id);
        String key = "updateList";
        // 查找缓存
        Object updateCache = redisService.get(key);
        if (updateCache != null) {
            List<Update> updates = MyCastUtils.objectConvertToList(updateCache, Update.class);
            // 删除缓存中应该删除的数据
            updates.removeIf(u -> u.getId() == id);
            // 更新缓存
            redisService.set(key, updates);
        }
    }
}
