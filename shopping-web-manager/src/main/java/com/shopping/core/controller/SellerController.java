package com.shopping.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shopping.core.pojo.seller.Seller;
import com.shopping.core.service.SellerService;
import entity.PageResult;
import entity.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
   private SellerService sellerService;


    /**
     * 分页条件查询
     *
     * @param page
     * @param rows
     * @param seller
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody Seller seller) {
        return sellerService.search(page, rows, seller);
    }

    /**
     * 详情回显
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public Seller findOne(String id) {
        return sellerService.findOne(id);
    }

    @RequestMapping("/updateStatus")
    public R updateStatus(String sellerId, String status) {
        try {
             sellerService.updateStatus(sellerId,status);
            return new R(true, "成功");
        } catch (Exception e) {
            return new R(false, "失败");
        }
    }


}
