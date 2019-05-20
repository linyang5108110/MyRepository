package com.shopping.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shopping.core.dao.ad.ContentCategoryDao;
import com.shopping.core.pojo.ad.ContentCategory;
import com.shopping.core.pojo.ad.ContentCategoryQuery;
import com.sun.tools.internal.xjc.model.CEnumConstant;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ContentCategoryServiceImpl  implements ContentCategoryService{

    @Autowired
   private ContentCategoryDao contentCategoryDao;

    @Override
    public PageResult search(Integer page, Integer rows, ContentCategory contentCategory) {

        PageHelper.startPage(page,rows);
        ContentCategoryQuery ccq = new ContentCategoryQuery();
        ContentCategoryQuery.Criteria criteria = ccq.createCriteria();
        if(null != contentCategory.getName() && !"".equals(contentCategory.getName().trim()))
        {
            criteria.andNameLike("%"+contentCategory.getName().trim()+"%");
        }
       Page<ContentCategory> page1 =  (Page<ContentCategory>)contentCategoryDao.selectByExample(ccq);

        return new PageResult(page1.getTotal(),page1.getResult());
    }

    @Override
    public ContentCategory findOne(Long id) {
       return contentCategoryDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(ContentCategory contentCategory) {
        contentCategoryDao.updateByPrimaryKeySelective(contentCategory);
    }

    @Override
    public void add(ContentCategory contentCategory) {
        contentCategoryDao.insertSelective(contentCategory);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
             contentCategoryDao.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<ContentCategory> findAll() {
        return contentCategoryDao.selectByExample(null);
    }


}
