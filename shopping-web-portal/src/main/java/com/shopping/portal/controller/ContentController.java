package com.shopping.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shopping.core.pojo.ad.Content;
import com.shopping.core.service.ContentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Reference
    ContentService contentService;

    @RequestMapping("/findByCategoryId")
    public List<Content> findByCategoryId(Long categoryId)
    {
      return   contentService.findByCategoryId(categoryId);
    }
}
