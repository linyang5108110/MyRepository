package com.shopping.core.service;

import com.shopping.core.pojo.seller.Seller;
import entity.PageResult;

public interface SellerService {
    void add(Seller seller);

    PageResult search(Integer page, Integer rows, Seller seller);

    Seller findOne(String id);

    void updateStatus(String sellerId, String status);

    Seller findUserName(String username);
}
