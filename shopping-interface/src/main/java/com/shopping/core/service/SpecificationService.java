package com.shopping.core.service;

import com.shopping.core.pojo.specification.Specification;
import entity.PageResult;
import vo.SpecificationVo;

import java.util.List;
import java.util.Map;

public interface SpecificationService {
    PageResult search(Integer page, Integer rows, Specification specification);

    void add(SpecificationVo specificationVo);

    SpecificationVo findOne(long id);

    void update(SpecificationVo specificationVo);

    void delete(Long[] ids);

    List<Map> selectOptionList();
}
