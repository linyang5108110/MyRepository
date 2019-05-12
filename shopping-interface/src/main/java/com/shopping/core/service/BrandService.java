package com.shopping.core.service;

import com.shopping.core.pojo.good.Brand;
import entity.PageResult;

import java.util.List;
import java.util.Map;

public interface BrandService {
    //查询品牌所有
    public List<Brand> findAll();

    PageResult findPage(Integer pageNum, Integer pageSize);

    void save(Brand brand);

    Brand findOne(Long id);

    void update(Brand brand);

    void delByIds(Long[] ids);

    PageResult search(Integer pageNum, Integer pageSize, Brand brand);

    List<Map> findList();
}
