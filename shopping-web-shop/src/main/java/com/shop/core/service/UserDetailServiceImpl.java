package com.shop.core.service;

import com.shopping.core.pojo.seller.Seller;
import com.shopping.core.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailServiceImpl implements UserDetailsService {

    private SellerService sellerService;
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Seller seller =  sellerService.findUserName(username);
        //判断用户是否存在
        if(seller != null)
        {
            //如果用户存在 判断是否审核通过
            if("1".equals(seller.getStatus()))
            {
                Set<GrantedAuthority> authorities = new HashSet<>();
                //加入权限
                authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
                return new User(seller.getSellerId(),seller.getPassword(),authorities);
            }
        }
        return null;
    }
}
