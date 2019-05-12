package com.shopping.core.service;

import com.shopping.core.pojo.item.ItemCat;
import com.shopping.core.pojo.template.TypeTemplate;
import entity.PageResult;

import java.util.List;

public interface ItemCatService {
    List<ItemCat> findByParentId(Long parentId);

    PageResult search(Integer page, Integer rows, ItemCat itemCat, Long parentId);

    void add(ItemCat itemCat,Long parentId);


    ItemCat findOne(Long id);

    List<ItemCat> findAll();
}
