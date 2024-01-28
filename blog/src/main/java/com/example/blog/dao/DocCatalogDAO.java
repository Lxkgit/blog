package com.example.blog.dao;

import com.example.blog.entity.DocCatalog;

import java.util.List;

public interface DocCatalogDAO {
    List<DocCatalog> selectDocCatalogList();
    List<DocCatalog> selectDocCatalogByParentId(Integer id);
    void insertDocCatalog(DocCatalog docCatalog);
    void updateDocCatalog(DocCatalog docCatalog);
    void deleteDocCatalogById(Integer id);
}
