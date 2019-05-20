package com.shopping.core.service;

import com.shopping.core.pojo.ad.ContentCategory;
import entity.PageResult;

import java.util.List;

public interface ContentCategoryService {
    PageResult search(Integer page, Integer rows, ContentCategory contentCategory);

    ContentCategory findOne(Long id);

    void update(ContentCategory contentCategory);

    void add(ContentCategory contentCategory);

    void delete(Long[] ids);

    List<ContentCategory> findAll();
}
