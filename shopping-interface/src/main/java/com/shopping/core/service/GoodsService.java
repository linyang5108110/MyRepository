package com.shopping.core.service;

import com.shopping.core.pojo.good.Goods;
import entity.GoodsVo;
import entity.PageResult;

public interface GoodsService {
    void add(GoodsVo goodsVo);

    PageResult search(Integer page, Integer rows, Goods goods);

    GoodsVo findOne(Long id);


    void update(GoodsVo vo);

    void updateStatus(Long[] ids, String status);

    void delete(Long[] ids);
}
