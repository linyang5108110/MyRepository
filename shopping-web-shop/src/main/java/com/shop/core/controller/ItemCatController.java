package com.shop.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shopping.core.pojo.item.ItemCat;
import com.shopping.core.pojo.template.TypeTemplate;
import com.shopping.core.service.ItemCatService;
import entity.PageResult;
import entity.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/findOne")
     public ItemCat findOne(Long id)
     {
         return itemCatService.findOne(id);
     }

     @RequestMapping("/findAll")
     public List<ItemCat> findAll()
     {
         return itemCatService.findAll();
     }
}
