package com.shopping.core.service;

import com.shopping.core.pojo.ad.Content;
import entity.PageResult;

import java.util.List;

public interface ContentService {
    List<Content> findByCategoryId(Long categoryId);

    PageResult search(Integer page, Integer rows, Content content);

    void add(Content content);

    Content findOne(Long id);

    void update(Content content);

    void delete(Long[] ids);
}
