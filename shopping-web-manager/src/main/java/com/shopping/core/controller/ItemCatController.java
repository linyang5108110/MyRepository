package com.shopping.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shopping.core.pojo.item.ItemCat;
import com.shopping.core.service.ItemCatService;
import entity.PageResult;
import entity.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.rmi.CORBA.Tie;
import java.util.List;

@RestController
@RequestMapping("/itemCat")
public class ItemCatController {

    @Reference
  private   ItemCatService itemCatService;

    @RequestMapping("/findByParentId")
    public List<ItemCat> findByParentId(Long parentId)
    {
        return itemCatService.findByParentId(parentId);
    }

    @RequestMapping("/search")
    public PageResult search(Integer page ,Integer rows,Long parentId,@RequestBody ItemCat searchEntity)
    {
        return itemCatService.search(page,rows,searchEntity,parentId);
    }


    @RequestMapping("/add")
    public R add(Long addToparentId, @RequestBody ItemCat itemCat)
    {
        try {
            itemCatService.add(itemCat,addToparentId);
           return new R(true,"成功");
        }catch (Exception e)
        {
           return new R(false,"失败");
        }
    }
}
