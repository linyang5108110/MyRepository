package com.shopping.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shopping.core.pojo.ad.ContentCategory;
import com.shopping.core.service.ContentCategoryService;
import entity.PageResult;
import entity.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contentCategory")
public class ContentCategoryController {


    @Reference
   private ContentCategoryService contentCategoryService;

    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody ContentCategory contentCategory) {
        return contentCategoryService.search(page, rows, contentCategory);
    }

    @RequestMapping("/findOne")
    public ContentCategory findOne(Long id) {
        return contentCategoryService.findOne(id);
    }

    @RequestMapping("/update")
    public R update(@RequestBody ContentCategory contentCategory) {
        try {
           contentCategoryService.update(contentCategory);
            return new R(true, "成功");
        } catch (Exception e) {
            return new R(false, "失败");
        }
    }

    @RequestMapping("/add")
    public R add(@RequestBody ContentCategory contentCategory)
    {
         try{

             contentCategoryService.add(contentCategory);
             return new R(true,"成功");
         }catch (Exception e)
         {
             return new R(false ,"失败");
         }
    }

    @RequestMapping("/delete")
    public R delete(Long[] ids)
    {
        try {
            contentCategoryService.delete(ids);
            return new R(true,"成功");
        }catch (Exception e)
        {
            return new R(false,"失败");
        }
    }

    /**
     * 找到所有contentCategory 为新增加content
     * @return
     */
    @RequestMapping("/findAll")
    public List<ContentCategory> findAll()
    {
      return contentCategoryService.findAll();
    }
}
