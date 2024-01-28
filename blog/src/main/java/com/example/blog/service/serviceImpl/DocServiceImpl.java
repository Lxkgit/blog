package com.example.blog.service.serviceImpl;

import com.example.blog.dao.DocContentDAO;
import com.example.blog.dao.DocCatalogDAO;
import com.example.blog.entity.DocContent;
import com.example.blog.entity.DocCatalog;
import com.example.blog.redis.RedisService;
import com.example.blog.service.DocService;
import com.example.blog.util.Contents;
import com.example.blog.util.MyCastUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: 308501
 * @time: 2021/11/15 14:04
 * @description :文档服务接口实现类-包括文档列表和文档内容
 */

@Service
public class DocServiceImpl implements DocService {

    @Resource
    private DocContentDAO docContentDAO;
    @Resource
    private DocCatalogDAO docCatalogDAO;
    @Autowired
    private RedisService redisService;

    // 文档目录缓存 key
    private static final String docCatalogKey = "docCatalog";
    // 文档内容缓存 key
    private static final String docContentKey = "docContent";

    @Override
    public List<DocCatalog> selectDocCatalogList() {
        Object docCatalogCache = redisService.get(docCatalogKey);
        List<DocCatalog> docCatalogs;
        if (docCatalogCache == null) {
            docCatalogs = docCatalogDAO.selectDocCatalogList();
            redisService.set(docCatalogKey, docCatalogs);
        } else {
            docCatalogs = MyCastUtils.objectConvertToList(docCatalogCache, DocCatalog.class);
        }
        handleMenus(docCatalogs);
        return docCatalogs;
    }

    @Override
    public DocContent selectDocContentById(Integer id) {
        Object docContentCache = redisService.get(docContentKey + id);
        if (docContentCache != null) {
            return (DocContent) docContentCache;
        }
        DocContent docContent = docContentDAO.selectDocContentById(id);
        redisService.set(docContentKey + id, docContent);
        return docContent;
    }

    /**
     * 新增文档，新增数据库中文档目录和内容 以及redis中文档目录  文档内容不写入redis
     *
     * @param docCatalog
     */
    @Override
    public void insertDoc(DocCatalog docCatalog) {
        Object docCatalogCache = redisService.get(docCatalogKey);
        // 写入数据库
        docCatalogDAO.insertDocCatalog(docCatalog);
        DocContent docContent = new DocContent();
        docContent.setId(docCatalog.getValue());
        docContent.setTitle(docCatalog.getTitle());
        docContentDAO.insertDocContent(docContent);

        if (docCatalogCache != null) {
            List<DocCatalog> docCatalogList = MyCastUtils.objectConvertToList(docCatalogCache, DocCatalog.class);
            docCatalogList.add(docCatalog);
            // 使用排序类对目录进行排序， 保证写入redis目录顺序正确， 排序安装id即value 从小到大排序
            Collections.sort(docCatalogList);
            redisService.set(docCatalogKey, docCatalogList);
        }
    }

    /**
     * 删除文档，包括内容和目录以及缓存
     *
     * @param id
     * @return
     */
    @Override
    public String deleteDoc(Integer id) {
        List<DocCatalog> docCatalogs;
        docCatalogs = docCatalogDAO.selectDocCatalogByParentId(id);
        if (docCatalogs.size() == 0) {
            // 删除缓存中的目录
            Object docCatalogCache = redisService.get(docCatalogKey);
            if (docCatalogCache != null) {
                List<DocCatalog> docCatalogList = MyCastUtils.objectConvertToList(docCatalogCache, DocCatalog.class);
                docCatalogList.removeIf(docCatalog -> docCatalog.getValue() == id);
                redisService.set(docCatalogKey, docCatalogList);
            }
            // 删除缓存中的内容
            Object docContentCache = redisService.get(docContentKey + id);
            if (docContentCache != null) {
                redisService.delete(docContentKey + id);
            }
            // 删除数据库中的目录和内容
            docCatalogDAO.deleteDocCatalogById(id);
            docContentDAO.deleteDocContentById(id);
        } else {
            return "请先删除该文档下的子文档";
        }
        return "文档删除成功";
    }

    /**
     * 更新文档目录
     *
     * @param docCatalog
     */
    @Override
    public void updateDocCatalog(DocCatalog docCatalog) {
        Object docCatalogCache = redisService.get(docCatalogKey);
        docCatalogDAO.updateDocCatalog(docCatalog);
        if (docCatalogCache != null) {
            List<DocCatalog> docCatalogs = MyCastUtils.objectConvertToList(docCatalogCache, DocCatalog.class);
            for (DocCatalog d : docCatalogs) {
                if (d.getValue() == docCatalog.getValue()) {
                    docCatalog.setTitle(d.getTitle());
                    docCatalogs.remove(d);
                    docCatalogs.add(docCatalog);
                    redisService.set(docCatalogKey, docCatalogs);
                    return;
                }
            }
        }

    }

    /**
     * 更新数据库中的文档信息和缓存中的文档数据
     *
     * @param docContent
     */
    @Override
    public void updateDocContent(DocContent docContent) {
        Object docContentCache = redisService.get(docContentKey + docContent.getId());
        if (docContentCache != null) {
            redisService.set(docContentKey + docContent.getId(), docContent);
        }
        docContentDAO.updateDocContent(docContent);
    }


    /**
     * 整理菜单
     * 将传入的list整理成树形结构，最后输出从小到大的排序
     *
     * @param types
     */
    @Override
    public void handleMenus(List<DocCatalog> types) {
        DocCatalog.sort = Contents.DESC;
        Collections.sort(types);
        for (int i = 0; i<types.size();i++) {
            DocCatalog d = types.get(i);
            if (d.getParent() != 0) {
                findParent(types, d);
                types.remove(d);
                i--;
            }
        }
        DocCatalog.sort = Contents.ACS;
        Collections.sort(types);
    }

    /**
     * 整理菜单
     * @param docCatalogList
     * @param d
     */
    @Override
    public void findParent(List<DocCatalog> docCatalogList, DocCatalog d) {
        for (DocCatalog docCatalog : docCatalogList) {
            if (docCatalog.getValue() == d.getParent()) {
                List<DocCatalog> child = docCatalog.getChildren();
                if (child == null) {
                    child = new ArrayList<>();
                }
                child.add(d);
                DocCatalog.sort = Contents.ACS;
                Collections.sort(child);
                docCatalog.setChildren(child);
            }
        }
    }
}
