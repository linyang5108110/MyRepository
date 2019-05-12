package com.shop.core.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.shopping.core.pojo.template.TypeTemplate;
import com.shopping.core.service.TypeTemplateService;
import entity.PageResult;
import entity.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

    @Reference
    TypeTemplateService typeTemplateService;

    @RequestMapping("/findOne")
    public TypeTemplate findOne(Long id)
    {
        return typeTemplateService.findOne(id);
    }


    @RequestMapping("/findBySpecList")
    public List<Map> findBySpecList(Long id)
    {
        return typeTemplateService.findBySpecList(id);
    }
}
