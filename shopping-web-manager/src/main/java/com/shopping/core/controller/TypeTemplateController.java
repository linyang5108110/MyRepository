package com.shopping.core.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.shopping.core.pojo.template.TypeTemplate;
import com.shopping.core.service.TypeTemplateService;
import entity.PageResult;
import entity.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

    @Reference
  private   TypeTemplateService typeTemplateService;

    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody TypeTemplate typeTemplate) {
        return typeTemplateService.search(page, rows, typeTemplate);
    }

    @RequestMapping("/add")
    public R add(@RequestBody TypeTemplate typeTemplate) {
        try {
            typeTemplateService.add(typeTemplate);
            return new R(true, "成功");
        } catch (Exception e) {
            return new R(false, "失败");
        }
    }

    @RequestMapping("/findOne")
    public TypeTemplate findOne(Long id)
    {
        return typeTemplateService.findOne(id);
    }

    @RequestMapping("/update")
    public R update(@RequestBody TypeTemplate typeTemplate) {
        try {
            typeTemplateService.update(typeTemplate);
            return new R(true, "成功");
        } catch (Exception e) {
            return new R(false, "失败");
        }
    }

    @RequestMapping("/delete")
    public R delete(Long[] ids)
    {
        try {
            typeTemplateService.delete(ids);
            return new R(true, "成功");
        } catch (Exception e) {
            return new R(false, "失败");
        }
    }
}
