package com.shopping.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shopping.core.dao.ad.ContentDao;
import com.shopping.core.pojo.ad.Content;
import com.shopping.core.pojo.ad.ContentQuery;
import entity.PageResult;
import org.opensaml.xml.security.credential.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDao contentDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Content> findByCategoryId(Long categoryId) {

        List<Content> contentList = (List<Content>) redisTemplate.boundHashOps("content").get(categoryId);
        if(null == contentList || contentList.size() == 0) {
            ContentQuery contentQuery = new ContentQuery();
            contentQuery.createCriteria().andCategoryIdEqualTo(categoryId).andStatusEqualTo("1");
            contentQuery.setOrderByClause("sort_order desc");
            contentList =  contentDao.selectByExample(contentQuery);

            //3:查询出来之后放到缓存中一份
            redisTemplate.boundHashOps("content").put(categoryId,contentList);
            //存活时间
            redisTemplate.boundHashOps("content").expire(8, TimeUnit.HOURS);
        }

        return contentList;

    }

    /**
     * 公告类容 search
     * @param page
     * @param rows
     * @param content
     * @return
     */
    @Override
    public PageResult search(Integer page, Integer rows, Content content) {
        PageHelper.startPage(page, rows);
        ContentQuery contentQuery = new ContentQuery();
        ContentQuery.Criteria criteria = contentQuery.createCriteria();
        if (null != content.getTitle() && !"".equals(content.getTitle().trim())) {
            criteria.andTitleLike("%" + content.getTitle().trim() + "%");
        }
        Page<Content> page1 = (Page<Content>) contentDao.selectByExample(contentQuery);
        return new PageResult(page1.getTotal(), page1.getResult());
    }

    @Override
    public void add(Content content) {
        redisTemplate.boundHashOps("content").delete(content.getCategoryId());
        contentDao.insertSelective(content);
    }

    @Override
    public Content findOne(Long id) {

       return   contentDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Content content) {
        Content content1 = contentDao.selectByPrimaryKey(content.getId());
        //判断是否修改了广告分类id
         if(!content1.getCategoryId().equals(content.getCategoryId()))
         {  //如果他们两个不一样证明修改了原本的广告分类id
             redisTemplate.boundHashOps("content").delete(content1.getCategoryId());
         }

        redisTemplate.boundHashOps("content").delete(content.getCategoryId());
        contentDao.updateByPrimaryKeySelective(content);
    }

    @Override
    public void delete(Long[] ids) {
        if(null != ids)
        {
            for (Long id : ids) {
                Content content = contentDao.selectByPrimaryKey(id);
                redisTemplate.boundHashOps("content").delete(content.getCategoryId());
                contentDao.deleteByPrimaryKey(id);
            }
        }

    }
}
