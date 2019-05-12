package com.shopping.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.core.dao.good.BrandDao;
import com.shopping.core.pojo.good.Brand;
import com.shopping.core.pojo.good.BrandQuery;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 品牌管理
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandDao brandDao;

    @Override
    public List<Brand> findAll() {
        return brandDao.selectByExample(null);
    }

    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        //分页插件
        PageHelper.startPage(pageNum, pageSize);

        List<Brand> brandList = brandDao.selectByExample(null);
        PageInfo pageInfo= new PageInfo(brandList);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public void save(Brand brand) {
        brandDao.insertSelective(brand);
    }

    @Override
    public Brand findOne(Long id) {
        return brandDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Brand brand) {
        brandDao.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delByIds(Long[] ids) {
        for (Long id : ids) {
            brandDao.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult search(Integer pageNum, Integer pageSize, Brand brand) {
        //分页插件
        PageHelper.startPage(pageNum, pageSize);
        //条件对象
        BrandQuery brandQuery = new BrandQuery();
        //创建内部条件对象
        BrandQuery.Criteria criteria = brandQuery.createCriteria();
        //判断名称是否会有值
        if(null != brand.getName() && !"".equals(brand.getName().trim()))
        {
            criteria.andNameLike("%"+brand.getName().trim()+"%");
        }
        if(null != brand.getFirstChar() && !"".equals(brand.getFirstChar().trim()))
        {
           criteria.andFirstCharEqualTo(brand.getFirstChar().trim());
        }
        List<Brand> brandList = brandDao.selectByExample(brandQuery);
        PageInfo pageInfo= new PageInfo(brandList);
        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public List<Map> findList() {

        List<Map> list = brandDao.findList();
        return list;
    }
}
