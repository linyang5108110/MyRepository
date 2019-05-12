package com.shopping.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shopping.core.dao.specification.SpecificationOptionDao;
import com.shopping.core.dao.template.TypeTemplateDao;
import com.shopping.core.pojo.specification.SpecificationOptionQuery;
import com.shopping.core.pojo.template.TypeTemplate;
import com.shopping.core.pojo.template.TypeTemplateQuery;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;


@Service
public class TypeTemplateServiceImpl implements TypeTemplateService {


    @Autowired
    private TypeTemplateDao typeTemplateDao;
    @Autowired
    private SpecificationOptionDao specificationOptionDao;

    @Override
    public PageResult search(Integer page, Integer rows, TypeTemplate typeTemplate) {
        //分页插件
        PageHelper.startPage(page,rows);
        //条件对象
        TypeTemplateQuery typeTemplateQuery = new TypeTemplateQuery();
        TypeTemplateQuery.Criteria criteria = typeTemplateQuery.createCriteria();
        if(null != typeTemplate.getName()  &&  !"".equals(typeTemplate.getName().trim()))
        {
             criteria.andNameLike("%"+typeTemplate.getName().trim()+"%");
        }
        Page<TypeTemplate> p = (Page<TypeTemplate>) typeTemplateDao.selectByExample(typeTemplateQuery);
        return new PageResult(p.getTotal(),p.getResult());
    }

    @Override
    public void add(TypeTemplate typeTemplate) {
        typeTemplateDao.insertSelective(typeTemplate);
    }

    @Override
    public TypeTemplate findOne(Long id) {

        return typeTemplateDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(TypeTemplate typeTemplate) {
        typeTemplateDao.updateByPrimaryKeySelective(typeTemplate);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            typeTemplateDao.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Map> findBySpecList(Long id) {

        //查询处类型模版数据
        TypeTemplate typeTemplate = typeTemplateDao.selectByPrimaryKey(id);
        //从模版数据中拿到规格数据
        String specIds = typeTemplate.getSpecIds();
        //规格数据是一个JSON字符串 这里我们要将规格数据转换成一个对象
        List<Map> maps = JSON.parseArray(specIds, Map.class);
         //循环这个maps
        for (Map map : maps) {
          //这个时候这个map里面有id 和text 这个id就是规格数据的id
          //使用和这个Id为条条件查询 规格对象

            SpecificationOptionQuery query = new SpecificationOptionQuery();
            //这有一个问题 因为查询条件接受的数据是一个long类型的id
            //但是map.get()取出来的是一个object 类型 object类型不能直接转换长整形
            //所以先转换成一个包装类型 在转换成一个long类型
            query.createCriteria().andSpecIdEqualTo((long)(Integer)map.get("id"));

            map.put("options",specificationOptionDao.selectByExample(query));

        }

        return maps;
    }
}
