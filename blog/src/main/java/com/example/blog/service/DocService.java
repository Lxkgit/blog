package com.example.blog.service;

import com.example.blog.entity.DocContent;
import com.example.blog.entity.DocCatalog;

import java.util.List;

/**
 * @author: 308501
 * @time: 2021/11/15 14:03
 * @description :文档相关服务-包括文档列表和文档内容
 */

public interface DocService {
    List<DocCatalog> selectDocCatalogList();
    DocContent selectDocContentById(Integer id);
    void insertDoc(DocCatalog docCatalog);
    String deleteDoc(Integer id);
    void updateDocCatalog(DocCatalog docCatalog);
    void updateDocContent(DocContent docContent);
    void handleMenus(List<DocCatalog> types);
    void findParent(List<DocCatalog> docCatalogList, DocCatalog d);
}
