package com.example.blog.service.serviceImpl;

import com.example.blog.dao.BlogDiaryDAO;
import com.example.blog.entity.Diary;
import com.example.blog.redis.RedisService;
import com.example.blog.service.BlogDiaryService;
import com.example.blog.util.MyCastUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

/**
 * @author: lxk
 * @time: 2021/8/7 13:58
 * @description :日记
 */

@Service
public class BlogDiaryServiceImpl implements BlogDiaryService {

    @Autowired
    private RedisService redisService;
    @Resource
    private BlogDiaryDAO blogDiaryDAO;

    /**
     * 利用分页插件查询日记
     * pageNum 为第几页
     * pageSize 为页大小
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Diary> selectDiaryList(int pageNum, int pageSize) {
        // 设置日记列表在redis的key
        String key = "diaryList";
        // 从redis中获取日记列表
        Object diaryCache = redisService.get(key);
        List<Diary> diaries;
        // redis中日记列表为空
        if (diaryCache == null) {
            // 分页插件，下一条mybatis语句按照设置分页
            PageHelper.startPage(pageNum, pageSize);
            // 查询全部日记，和文章一样有待优化
            diaries = blogDiaryDAO.selectDiaryList();
            // 将查到的结果放回redis
            redisService.set(key, diaries);
        } else {
            // 缓存中有日记数据，直接取出使用
            diaries = MyCastUtils.objectConvertToList(diaryCache, Diary.class);
        }
        // 对日记数据逆序处理，因为日记存入数据库是按照时间顺序（写入数据库的时间，不是日记的date列）排列的，逆序之后便于查看，即最新写的日记在页面上展示在最前面
        Collections.reverse(diaries);
        // 分页获取数据
        PageInfo<Diary> pageInfo = new PageInfo(diaries, pageSize);
        return pageInfo;
    }

    /**
     * 根据日期查询日记
     *
     * @param date
     * @return
     */
    @Override
    public Diary selectDiaryByCreatTime(Date date) {
        // redis的key
        String key = "diaryList";
        // 从redis中获取日记数据
        Object diaryCache = redisService.get(key);
        // 获取到了日记数据
        if (diaryCache != null) {
            // 缓存中的数据转化为list
            List<Diary> diaries = MyCastUtils.objectConvertToList(diaryCache, Diary.class);
            // 从缓存中查找指定日期的日记
            for (Diary diary : diaries) {
                // 由于多次试验 if (date.equals(diary.getCreate_time())) 返回结果始终为 false 所以将其转化为String再比较，完美解决
                // 算是一个小遗留bug以后再回来看
                if (date.toString().equals(diary.getCreate_time().toString())) {
                    return diary;
                }
            }
        }
        // 缓存中查找失败，从数据库中查找
        return blogDiaryDAO.selectDiaryByCreatTime(date);
    }

    /**
     * 修改已完成的日记
     *
     * @param diary
     */
    @Override
    public void updateDiaryById(Diary diary) {
        // redis的key
        String key = "diaryList";
        // 从数据库中修改日记
        blogDiaryDAO.updateDiaryById(diary);
        // 从redis中获取日记列表
        Object diaryCache = redisService.get(key);
        // 获取到了日记列表
        if (diaryCache != null) {
            // 整理缓存中日记的格式
            List<Diary> diaries = MyCastUtils.objectConvertToList(diaryCache, Diary.class);
            // 遍历查找修改的那一篇日记
            for (Diary d : diaries) {
                // 修改的日记id是不为空的
                if (d.getId() == diary.getId()) {
                    // 移除旧的日记
                    diaries.remove(d);
                    // 添加新的日记
                    diaries.add(diary);
                    // 缓存刷新
                    redisService.set(key, diaries);
                    // 退出循环
                    return;
                }
            }
        }
    }

    /**
     * 新增日记
     *
     * @param diary
     */
    @Override
    public void insertDiary(Diary diary) {
        // 向数据库中写入新的日记
        // 此处真正感受到了 mybatis 的 nb
        // 从前端传入的diary数据只有日期和内容（因为数据库中就只有日期、内容和id，但是id是自增序列，所以不需要传入）
        // 通过 mybatis 的语句
        //  <selectKey resultType="java.lang.Integer" keyProperty="id" order="AFTER" >
        //      SELECT LAST_INSERT_ID()
        //  </selectKey>
        // 将新增日记自增id返回，然后可以使用此数据直接更新redis提高了效率，此处和博客文章的新增相同
        blogDiaryDAO.insertDiary(diary);
        // redis的key
        String key = "diaryList";
        // 根据key查找值
        Object diaryCache = redisService.get(key);
        // redis中日记列表不为空
        if (diaryCache != null) {
            // 格式整理
            List<Diary> diaries = MyCastUtils.objectConvertToList(diaryCache, Diary.class);
            // 向缓存中加入日记
            diaries.add(diary);
            // 刷新redis
            redisService.set(key, diaries);
        }
    }

    /**
     * 通过id删除日记
     *
     * @param id
     */
    @Override
    public void deleteDiaryById(Integer id) {
        // 根据id删除日记
        blogDiaryDAO.deleteDiaryById(id);
        // redis的key
        String key = "diaryList";
        // 从redis中获取日记列表
        Object diaryCache = redisService.get(key);
        // 获取到了日记列表
        if (diaryCache != null) {
            // 格式整理
            List<Diary> diaries = MyCastUtils.objectConvertToList(diaryCache, Diary.class);
            // 从redis中移除要删除的日记
            diaries.removeIf(diary -> diary.getId() == id);
            // 刷新redis
            redisService.set(key, diaries);
        }
    }
}
