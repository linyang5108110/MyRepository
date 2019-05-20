package com.shop.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shopping.core.pojo.seller.Seller;
import com.shopping.core.service.SellerService;
import entity.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Reference
   private SellerService sellerService;

    @RequestMapping("/add")
    public R add(@RequestBody Seller seller) {
        try {
            sellerService.add(seller);
            return new R(true, "成功");
        } catch (Exception e) {
            return new R(false, "失败");
        }
    }

}
