package com.shop.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shopping.core.pojo.good.Goods;
import com.shopping.core.service.GoodsService;
import entity.GoodsVo;
import entity.PageResult;
import entity.R;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private GoodsService goodsService;

    //商品添加
    @RequestMapping("/add")
    public R add(@RequestBody GoodsVo goodsVo) {
        try {
          //获取当前登陆人id
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            goodsVo.getGoods().setSellerId(name);
            goodsService.add(goodsVo);
            return  new R(true,"成功");
        } catch (Exception e) {
            e.printStackTrace();
            return  new R(false,"失败");
        }
    }

    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody Goods goods)
    {
         String name = SecurityContextHolder.getContext().getAuthentication().getName();
         goods.setSellerId(name);
         return goodsService.search(page,rows,goods);
    }
}
