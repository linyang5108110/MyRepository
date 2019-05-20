package com.shopping.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shopping.core.pojo.good.Goods;
import com.shopping.core.pojo.item.ItemCat;
import com.shopping.core.service.GoodsService;
import com.shopping.core.service.ItemCatService;
import entity.PageResult;
import entity.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Reference
    private   GoodsService goodsService;

    @Reference
   private ItemCatService itemCatService;

    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody Goods goods)
    {
       return goodsService.search(page,rows,goods);
    }

    /**
     * 查询所有分类
     */
    @RequestMapping("/findAll")
    public List<ItemCat> findAll()
    {
       return itemCatService.findAll();
    }


    /**
     * 商品审核通过/及驳回
     * @param ids
     * @param status
     * @return
     */
    @RequestMapping("/updateStatus")
    public R updateStatus(Long[] ids,String status)
    {
        try {
            goodsService.updateStatus(ids,status);
            return new R(true,"成功");
        }catch (Exception e)
        {
            return new R(false,"失败");
        }

    }

    /**
     * 商品下架，不真删除
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public  R delete(Long[] ids)
    {
        try {
            goodsService.delete(ids);
            return  new R(true,"success");
        }catch (Exception e )
        {
            return new R(false  ,"失败");
        }
    }

}
