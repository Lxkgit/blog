package com.example.blog.dao;

import com.example.blog.entity.DocContent;

public interface DocContentDAO {
    DocContent selectDocContentById(Integer id);
    void insertDocContent(DocContent docContent);
    void updateDocContent(DocContent docContent);
    void deleteDocContentById(Integer id);
}
