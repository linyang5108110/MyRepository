package com.shopping.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shopping.core.dao.item.ItemCatDao;
import com.shopping.core.pojo.item.ItemCat;
import com.shopping.core.pojo.item.ItemCatQuery;
import com.shopping.core.pojo.template.TypeTemplate;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ItemCatServiceImpl  implements  ItemCatService{

    @Autowired
    ItemCatDao itemCatDao;

    @Override
    public List<ItemCat> findByParentId(Long parentId) {

        System.out.println(parentId);
        //安父id条件查询
        ItemCatQuery itemCatQuery = new ItemCatQuery();
        ItemCatQuery.Criteria criteria = itemCatQuery.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return itemCatDao.selectByExample(itemCatQuery);
    }

    @Override
    public PageResult search(Integer page, Integer rows, ItemCat itemCat, Long parentId) {

        PageHelper.startPage(page,rows);
        ItemCatQuery itemCatQuery = new ItemCatQuery();
        ItemCatQuery.Criteria criteria = itemCatQuery.createCriteria();
        if(null != itemCat.getName() && !"".equals(itemCat.getName().trim()))
        {
            criteria.andNameLike("%"+itemCat.getName().trim()+"%");
        }
        criteria.andParentIdEqualTo(parentId);
       Page<ItemCat> itemCatPage = (Page<ItemCat>) itemCatDao.selectByExample(itemCatQuery);
        return new PageResult(itemCatPage.getTotal(),itemCatPage.getResult());
    }

    @Override
    public void add(ItemCat itemCat,Long parentId) {
          itemCat.setParentId(parentId);
          itemCatDao.insertSelective(itemCat);
    }

    @Override
    public ItemCat findOne(Long id) {

        return itemCatDao.selectByPrimaryKey(id);
    }

    @Override
    public List<ItemCat> findAll() {

        return itemCatDao.selectByExample(null);
    }
}
