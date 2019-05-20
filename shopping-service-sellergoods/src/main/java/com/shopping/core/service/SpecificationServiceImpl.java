package com.shopping.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shopping.core.dao.specification.SpecificationDao;
import com.shopping.core.dao.specification.SpecificationOptionDao;
import com.shopping.core.pojo.specification.Specification;
import com.shopping.core.pojo.specification.SpecificationOption;
import com.shopping.core.pojo.specification.SpecificationOptionQuery;
import com.shopping.core.pojo.specification.SpecificationQuery;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import vo.SpecificationVo;

import java.util.List;
import java.util.Map;

/**
 * 规格管理
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {
    @Autowired
   private SpecificationDao specificationDao;

    @Autowired
   private SpecificationOptionDao specificationOptionDao;


    @Override
    public PageResult search(Integer page, Integer rows, Specification specification) {
        PageHelper.startPage(page, rows);
        //创建条件对象
        SpecificationQuery specificationQuery = new SpecificationQuery();
        SpecificationQuery.Criteria criteria = specificationQuery.createCriteria();
        if(null != specification.getSpecName() && !"".equals(specification.getSpecName().trim()))
        {
            criteria.andSpecNameLike("%"+specification.getSpecName().trim()+"%");
        }
//        List<Specification> specificationList = specificationDao.selectByExample(null);
        Page<Specification> specifications = (Page<Specification>) specificationDao.selectByExample(specificationQuery);
        return new PageResult(specifications.getTotal(), specifications.getResult());
    }

    @Override
    public void add(SpecificationVo specificationVo) {
        //规格表
        specificationDao.insertSelective(specificationVo.getSpecification());

        //规格表选项
        List<SpecificationOption> specificationOptionList = specificationVo.getSpecificationOptionList();
        for (SpecificationOption specificationOption : specificationOptionList) {
            specificationOption.setSpecId(specificationVo.getSpecification().getId());
            specificationOptionDao.insertSelective(specificationOption);
        }
    }

    @Override
    public SpecificationVo findOne(long id) {
        SpecificationVo vo = new SpecificationVo();
        //规格对象
        vo.setSpecification(specificationDao.selectByPrimaryKey(id));
        //规格选项 创建一个条件对象
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        //再创建一个条件对象，加上条件 andSpecIdEqualTo 查询id等于id的结果集
        query.createCriteria().andSpecIdEqualTo(id);
        //再将条件对象加入查询中
        vo.setSpecificationOptionList(specificationOptionDao.selectByExample(query));

        return vo;
    }

    @Override
    public void update(SpecificationVo specificationVo) {
        //通过id修改规格
        specificationDao.updateByPrimaryKeySelective(specificationVo.getSpecification());

        //删除规格选项，在进行添加
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        //再创建一个条件对象，加上条件 andSpecIdEqualTo 查询id等于id的结果集
        query.createCriteria().andSpecIdEqualTo(specificationVo.getSpecification().getId());
        //通过条件对象删除
        specificationOptionDao.deleteByExample(query);

        //在进行添加
        List<SpecificationOption> specificationOptionList = specificationVo.getSpecificationOptionList();
        for (SpecificationOption specificationOption : specificationOptionList) {
            specificationOption.setSpecId(specificationVo.getSpecification().getId());
            specificationOptionDao.insertSelective(specificationOption);
        }
    }

    @Override
    public void delete(Long[] ids) {
        //删除规格选项
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        for (Long id : ids) {
            query.createCriteria().andSpecIdEqualTo(id);
            specificationOptionDao.deleteByExample(query);
        }

        //再循环删除规格
        for (Long id : ids) {
               specificationDao.deleteByPrimaryKey(id);
        }

    }

    @Override
    public List<Map> selectOptionList() {

        return specificationDao.selectOptionList();
    }


}
