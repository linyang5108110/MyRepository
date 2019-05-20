package com.shopping.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shopping.core.dao.seller.SellerDao;
import com.shopping.core.pojo.seller.Seller;
import com.shopping.core.pojo.seller.SellerQuery;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SellerServiceImpl implements SellerService {

    @Autowired
   private SellerDao sellerDao;

    @Override
    public void add(Seller seller) {
        //将密码加密
        seller.setPassword( new BCryptPasswordEncoder().encode(seller.getPassword()));
        //设置状态
        seller.setStatus("0");
        //保存
        sellerDao.insertSelective(seller);
    }

    @Override
    public PageResult search(Integer page, Integer rows, Seller seller) {

        PageHelper.startPage(page,rows);
        SellerQuery sellerQuery = new SellerQuery();
        SellerQuery.Criteria criteria = sellerQuery.createCriteria();
        //判断公司名称是否为null  /  ""
        if(null != seller.getName() && !"".equals(seller.getName().trim()))
        {
            criteria.andNameLike("%"+seller.getName().trim()+"%");
        }
        //判断店铺名称是否为null / ""
        if(null != seller.getNickName() && !"".equals(seller.getNickName().trim()))
        {
            criteria.andNickNameLike("%"+seller.getNickName().trim()+"%");
        }
        Page<Seller> p = (Page<Seller>) sellerDao.selectByExample(sellerQuery);

        return new PageResult(p.getTotal(),p.getResult());
    }

    @Override
    public Seller findOne(String id) {

        return sellerDao.selectByPrimaryKey(id);
    }

    @Override
    public void updateStatus(String sellerId, String status) {
        Seller seller = new Seller();
        seller.setSellerId(sellerId);
        seller.setStatus(status);
        sellerDao.updateByPrimaryKeySelective(seller);
    }

    /**
     * 安全框架用户验证
     * @param username
     * @return
     */
    @Override
    public Seller findUserName(String username) {

        return sellerDao.selectByPrimaryKey(username);
    }
}
