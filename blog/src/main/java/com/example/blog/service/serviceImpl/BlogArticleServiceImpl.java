package com.example.blog.service.serviceImpl;

import com.example.blog.dao.ArticleLabelDAO;
import com.example.blog.dao.ArticleTypeDAO;
import com.example.blog.dao.BlogArticleDAO;
import com.example.blog.entity.Article;
import com.example.blog.entity.ArticleLabel;
import com.example.blog.entity.ArticleType;
import com.example.blog.redis.RedisService;
import com.example.blog.service.BlogArticleService;
import com.example.blog.util.MyCastUtils;
import com.example.blog.util.MyPage;
import com.example.blog.util.MyPageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: lxk
 * @date: 2021/7/5 13:36
 * @description: 博客文章服务类
 * @modified By:
 */
@Service
public class BlogArticleServiceImpl implements BlogArticleService {

    @Autowired
    private RedisService redisService;
    @Resource
    private BlogArticleDAO blogArticleDAO;
    @Resource
    private ArticleTypeDAO articleTypeDAO;
    @Resource
    private ArticleLabelDAO articleLabelDAO;

    /**
     * 查找博客中的全部文章，感觉还有优化空间
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public MyPage<Article> selectArticleList(int pageNum, int pageSize) {
        // 博客文章缓存 key
//        String key = "articleList";
        // 从redis中获取文章
//        Object articleCache = redisService.get(key);
        List<Article> articles;
//        if (articleCache == null) {
            articles = blogArticleDAO.selectArticleList((pageNum-1)*pageSize, pageSize);
            // 将查出来的文章放在缓存
//            redisService.set(key, articles);
//        } else {
//            // 缓存中有文章列表，直接从缓存中获取
//            articles = MyCastUtils.objectConvertToList(articleCache, Article.class);
//        }

        for (Article a : articles) {
            String articleType = a.getArticleType();
            String articleLabel = a.getArticleLabel();
            String[] types = articleType.split("\\$");
            String[] labels = articleLabel.split("\\$");
            List<ArticleType> articleTypes = new ArrayList<>();
            List<ArticleLabel> articleLabels = new ArrayList<>();
            for (String s : types) {
                int type = Integer.parseInt(s);
                articleTypes.add(articleTypeDAO.selectArticleTypeById(type));
            }
            for (String s : labels) {
                int label = Integer.parseInt(s);
                articleLabels.add(articleLabelDAO.selectArticleLabelById(label));

            }
            a.setLabels(articleLabels);
            a.setTypes(articleTypes);
        }
        MyPage<Article> myPage = null;
        try {
            Integer count = blogArticleDAO.selectArticleCount();
            myPage = MyPageUtils.pageUtil(articles, pageNum, pageSize, count);
        } catch (Exception e){
            e.printStackTrace();
        }
        return myPage;
    }

    /**
     * 负责根据博客文章id查找文章
     * @param id
     * @return
     */
    @Override
    public Article selectArticleById(int id) {
        // 设置redis的key
        String key = "articleList";
        // 从缓存中获取文章信息
        Object articleCache = redisService.get(key);
        // 如果缓存中有文章信息
        if (articleCache != null) {
            // 将缓存中的数据整理为list，以便进一步处理
            List<Article> articles = MyCastUtils.objectConvertToList(articleCache, Article.class);
            // 从缓存中获取的list查询需要的文章
            for (Article a : articles) {
                if (a.getId() == id) {
                    return a;
                }
            }
        }
        // 查不到就从数据库中获取 （应该是不会用到的，不过为了保险也需要写上）
        return blogArticleDAO.selectArticleById(id);
    }

    /**
     *
     * @param article
     */
    @Override
    public void insertArticle(Article article) {
        // 新增的博客文章，写入数据库
        blogArticleDAO.insertArticle(article);
        // 对文章使用的分类和标签
        String[] types = article.getArticleType().split("\\$");
        String[] labels = article.getArticleLabel().split("\\$");
        for (String s : types) {
            ArticleType articleType = articleTypeDAO.selectArticleTypeById(Integer.parseInt(s));
            articleType.setNum(articleType.getNum() + 1);
            articleTypeDAO.updateArticleType(articleType);
        }
        for (String s : labels) {
            ArticleLabel articleLabel = articleLabelDAO.selectArticleLabelById(Integer.parseInt(s));
            articleLabel.setArticleNum(articleLabel.getArticleNum() + 1);
            articleLabelDAO.updateArticleLabel(articleLabel);
        }

//        // 设置redis的key
//        String key = "articleList";
//        // 从缓存中获取博客文章信息
//        Object articleCache = redisService.get(key);
//        // 新增的博客文章，写入数据库
//        blogArticleDAO.insertArticle(article);
//        // 缓存中有文章信息
//        if (articleCache != null) {
//            // 将缓存中的文章信息转化为list
//            List<Article> articles = MyCastUtils.objectConvertToList(articleCache, Article.class);
//            // 在list中添加新增的文章
//            articles.add(article);
//            // 再将修改的list写入redis
//            redisService.set(key, articles);
//        }
    }

    @Override
    public void updateArticle(Article article) {
        Article oldArticle = blogArticleDAO.selectArticleById(article.getId());
        if (!oldArticle.getArticleType().equals(article.getArticleType())){
            String[] oldType = oldArticle.getArticleType().split("\\$");
            String[] type = article.getArticleType().split("\\$");
            for (String s : oldType) {
                ArticleType articleType = articleTypeDAO.selectArticleTypeById(Integer.parseInt(s));
                articleType.setNum(articleType.getNum() - 1);
                articleTypeDAO.updateArticleType(articleType);
            }
            for (String s : type) {
                ArticleType articleType = articleTypeDAO.selectArticleTypeById(Integer.parseInt(s));
                articleType.setNum(articleType.getNum() + 1);
                articleTypeDAO.updateArticleType(articleType);
            }
        }
        if (!oldArticle.getArticleLabel().equals(article.getArticleLabel())){
            String[] oldLabel = oldArticle.getArticleLabel().split("\\$");
            String[] label = article.getArticleLabel().split("\\$");
            for (String s : oldLabel) {
                ArticleLabel articleLabel = articleLabelDAO.selectArticleLabelById(Integer.parseInt(s));
                articleLabel.setArticleNum(articleLabel.getArticleNum() - 1);
                articleLabelDAO.updateArticleLabel(articleLabel);
            }
            for (String s : label) {
                ArticleLabel articleLabel = articleLabelDAO.selectArticleLabelById(Integer.parseInt(s));
                articleLabel.setArticleNum(articleLabel.getArticleNum() + 1);
                articleLabelDAO.updateArticleLabel(articleLabel);
            }
        }
        blogArticleDAO.updateArticle(article);


//        // 设置redis的key
//        String key = "articleList";
//        // 从缓存中获取博客文章信息
//        Object articleCache = redisService.get(key);
//        // 从前端传入的文章id不等于0 即传入的文章有id信息，即为修改文章  因为文章id是数据库中的自增序列，前端是不会传id信息的，所以当id有值必然是此文章已经存在
//        // 修改文章信息
//        blogArticleDAO.updateArticle(article);
//        // 缓存不为空
//        if (articleCache != null) {
//            // 将缓存中的文章信息转化为list
//            List<Article> articles = MyCastUtils.objectConvertToList(articleCache, Article.class);
//            for (Article a : articles) {
//                // 将数据库中修改的文章同步修改到缓存中
//                if (a.getId() == article.getId()) {
//                    // 在缓存中找到修改的文章，并将它移除
//                    articles.remove(a);
//                    // 将新修改的文章写入list
//                    articles.add(article);
//                    // 将修改之后的list再放回redis
//                    redisService.set(key, articles);
//                    // 此处return是完成了文章的修改，停止继续循环（没有会报错，还不清楚原因）
//                    return;
//                }
//            }
//        }
    }

    /**
     * 负责删除指定id的博客文章
     * @param id
     */
    @Override
    public void deleteArticleById(int id) {
        Article article = blogArticleDAO.selectArticleById(id);
        String[] types = article.getArticleType().split("\\$");
        String[] labels = article.getArticleLabel().split("\\$");
        for (String s : types) {
            ArticleType articleType = articleTypeDAO.selectArticleTypeById(Integer.parseInt(s));
            articleType.setNum(articleType.getNum() - 1);
            articleTypeDAO.updateArticleType(articleType);
        }
        for (String s : labels) {
            ArticleLabel articleLabel = articleLabelDAO.selectArticleLabelById(Integer.parseInt(s));
            articleLabel.setArticleNum(articleLabel.getArticleNum() - 1);
            articleLabelDAO.updateArticleLabel(articleLabel);
        }
        blogArticleDAO.deleteArticleById(id);



//        // 从数据库中删除指定id的博客文章
//        blogArticleDAO.deleteArticleById(id);
//        // 设置redis的key
//        String key = "articleList";
//        // 从redis中获取指定key的值
//        Object articleCache = redisService.get(key);
//        // 缓存不为空
//        if (articleCache != null) {
//            // 将缓存信息转化为list
//            List<Article> articles = MyCastUtils.objectConvertToList(articleCache, Article.class);
//            // 移除缓存中id为指定id的文章
//            // 等价于
//            //  for (Article article : articles) {
//            //      if (article.getId() == id) {
//            //          articles.remove(article);
//            //      }
//            //  }
//            articles.removeIf(article -> article.getId() == id);
//            // 将修改之后的list保存如redis
//            redisService.set(key, articles);
//        }
    }
}
