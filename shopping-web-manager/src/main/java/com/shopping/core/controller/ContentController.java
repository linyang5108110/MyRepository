package com.shopping.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shopping.core.pojo.ad.Content;
import com.shopping.core.service.ContentService;
import com.sun.org.apache.regexp.internal.REUtil;
import entity.PageResult;
import entity.R;
import org.opensaml.xml.encryption.impl.ReferenceTypeUnmarshaller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Reference
  private   ContentService contentService;

    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody Content content) {
        return contentService.search(page, rows, content);
    }

    @RequestMapping("/add")
    public R add(@RequestBody Content content) {
        try {

            contentService.add(content);
            return new R(true, "成功");
        } catch (Exception e) {
            return new R(false, "失败");
        }
    }

    @RequestMapping("/findOne")
    public Content findOne(Long id) {

        return contentService.findOne(id);
    }

    @RequestMapping("/update")
    public R update(@RequestBody Content content) {
        try {

            contentService.update(content);
            return new R(true, "成功");
        } catch (Exception e) {
            return new R(false, "失败");
        }
    }

    @RequestMapping("/delete")
    public R delete(Long[] ids) {
        try {
            contentService.delete(ids);
            return new R(true, "成功");
        } catch (Exception e) {
            return new R(false, "失败");

        }
    }
}
