package com.example.blog.service.serviceImpl;

import com.example.blog.dao.ArticleTypeDAO;
import com.example.blog.entity.AdminMenu;
import com.example.blog.entity.ArticleType;
import com.example.blog.redis.RedisService;
import com.example.blog.service.ArticleTypeService;
import com.example.blog.util.MyCastUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleTypeServiceImpl implements ArticleTypeService {

    @Autowired
    private RedisService redisService;
    @Resource
    private ArticleTypeDAO articleTypeDAO;

    /**
     * 查询文章类型列表
     * @return
     */
    @Override
    public List<ArticleType> selectArticleTypeList() {
        // 设置文章标签在redis的key
        String key = "articleTypeList";
        // 从redis中尝试获取文章类型
        Object articleTypeCache = redisService.get(key);
        List<ArticleType> articleTypes;
        // 获取失败
        if (articleTypeCache == null) {
            // 查询数据库
            articleTypes = articleTypeDAO.selectArticleTypeList();
            // 保存文章类型到redis
            redisService.set(key,articleTypes);
        } else {
            // 获取成功，直接整理格式使用
            articleTypes = MyCastUtils.objectConvertToList(articleTypeCache, ArticleType.class);
        }
        return articleTypes;
    }

    @Override
    public List<ArticleType> selectArticleTypeTree() {
        String key = "articleTypeList";
        Object articleListCache = redisService.get(key);
        List<ArticleType> articleList;
        List<ArticleType> articleTree;
        if (articleListCache != null) {
            articleTree = MyCastUtils.objectConvertToList(articleListCache, ArticleType.class);
        } else {
            articleTree = articleTypeDAO.selectArticleTypeList();
            redisService.set(key, articleTree);
        }
        handleMenus(articleTree);
        return articleTree;
    }

    /**
     * 通过id查询文章类型
     * @param id
     * @return
     */
    @Override
    public ArticleType selectArticleTypeById(int id) {
        // 设置文章标签在redis中的key
        String key = "articleTypeList";
        // 从redis中获取文章标签
        Object articleTypeCache = redisService.get(key);
        // 获取成功
        if (articleTypeCache != null) {
            // 整理文章标签的格式
            List<ArticleType> articleTypes = MyCastUtils.objectConvertToList(articleTypeCache, ArticleType.class);
            // 查询出需要的标签返回
            for (ArticleType a : articleTypes) {
                if (a.getValue() == id) {
                    return a;
                }
            }
        }
        // 获取失败，查询数据库
        return articleTypeDAO.selectArticleTypeById(id);
    }

    /**
     * 查询全部文章类型并且分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<ArticleType> selectArticleTypeListPage(int pageNum, int pageSize) {
        // 设置redis中key
        String key = "articleTypeList";
        // 从redis中获取数据
        Object articleTypeCache = redisService.get(key);
        List<ArticleType> articleTypes;
        // 获取失败
        if (articleTypeCache == null) {
            // 添加分页查询，此语句下一条mybatis语句会按照设置分页
            PageHelper.startPage(pageNum, pageSize);
            // 查询数据
            articleTypes = articleTypeDAO.selectArticleTypeList();
            // 缓存
            redisService.set(key, articleTypes);
        } else {
            // 获取成功，整理数据
            articleTypes = MyCastUtils.objectConvertToList(articleTypeCache, ArticleType.class);
        }
        // 返回分页的数据
        PageInfo<ArticleType> pageInfo = new PageInfo(articleTypes, pageSize);
        return pageInfo;
    }

    /**
     * 更新标签
     * @param articleType
     */
    @Override
    public void updateArticleType(ArticleType articleType) {
        // 首先对数据库更新
        articleTypeDAO.updateArticleType(articleType);
        // redis的key
        String key = "articleTypeList";
        // 从redis中获取数据
        Object articleTypeCache = redisService.get(key);
        // 获取数据成功
        if (articleTypeCache != null) {
            // 整理缓存，将数据转化为list格式
            List<ArticleType> articleTypes = MyCastUtils.objectConvertToList(articleTypeCache, ArticleType.class);
            // 移除旧的文章标签缓存，添加新的文章标签缓存
            for (ArticleType a : articleTypes) {
                if (a.getValue() == articleType.getValue()) {
                    articleTypes.remove(a);
                    articleTypes.add(articleType);
                    redisService.set(key, articleTypes);
                    // 完成更新之后停止循环
                    return;
                }
            }
        }
    }

    /**
     * 新增标签
     * @param articleType
     */
    @Override
    public void insertArticleType(ArticleType articleType) {
        // 新增标签
        articleTypeDAO.insertArticleType(articleType);
        // 设置redis的key
        String key = "articleTypeList";
        // 从redis中获取数据
        Object articleTypeCache = redisService.get(key);
        // 向缓存中添加新增的文章标签
        if (articleTypeCache != null) {
            List<ArticleType> articleTypes = MyCastUtils.objectConvertToList(articleTypeCache, ArticleType.class);
            articleTypes.add(articleType);
            redisService.set(key, articleTypes);
        }
    }

    /**
     * 删除标签
     * @param id
     */
    @Override
    public void deleteArticleTypeById(int id) {
        // 从数据库中删除文章标签
        articleTypeDAO.deleteArticleTypeById(id);
        String key = "articleTypeList";
        Object articleTypeCache = redisService.get(key);
        // 从缓存中删除文章标签
        if (articleTypeCache != null) {
            List<ArticleType> articleTypes = MyCastUtils.objectConvertToList(articleTypeCache, ArticleType.class);
            // 查找到文章标签之后进行删除
            articleTypes.removeIf(a -> a.getValue() == id);
            // 更新缓存
            redisService.set(key,articleTypes);
        }
    }

    /**
     * 查询当前id下的子菜单
     * @param parentId
     * @return
     */
    @Override
    public List<ArticleType> selectArticleTypeByParentId(int parentId) {
        // 根据当前菜单id查询其它菜单的"parent_id"列是否为当前菜单
        return articleTypeDAO.selectArticleTypeByParentId(parentId);
    }

    /**
     * 整理菜单
     * @param types
     */
    @Override
    public void handleMenus(List<ArticleType> types) {
        // 移除父id不为0的全部菜单，因为父id不为0意味着是子菜单
        types.removeIf(m -> m.getParent() != 0);
        // 循环每一个一级菜单，查找其子菜单
        types.forEach(m -> {
            // 根据一级菜单的id查询其子菜单
            List<ArticleType> children = selectArticleTypeByParentId(m.getValue());
            // 设置子菜单
            m.setChildren(children);
        });
    }
}
