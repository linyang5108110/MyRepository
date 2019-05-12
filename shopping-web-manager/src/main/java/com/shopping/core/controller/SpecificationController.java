package com.shopping.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shopping.core.pojo.specification.Specification;
import com.shopping.core.service.SpecificationService;
import entity.PageResult;
import entity.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.SpecificationVo;

import java.util.List;
import java.util.Map;

/**
 * 规格管理
 */
@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @Reference
    private SpecificationService specificationService;

    /**
     * 分页查询
     *
     * @param page
     * @param rows
     * @param specification
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(Integer page, Integer rows, @RequestBody Specification specification) {
        return specificationService.search(page, rows, specification);
    }

    /**
     * 添加
     *
     * @param specificationVo
     * @return
     */
    @RequestMapping("/add")
    public R add(@RequestBody SpecificationVo specificationVo) {
        try {
            specificationService.add(specificationVo);
            return new R(true, "成功");
        } catch (Exception e) {
            return new R(false, "失败");
        }

    }

    /**
     * 修改
     * @param specificationVo
     * @return
     */
    @RequestMapping("/update")
    public R update(@RequestBody SpecificationVo specificationVo) {
        try {
            specificationService.update(specificationVo);
            return new R(true, "成功");
        } catch (Exception e) {
            return new R(false, "失败");
        }

    }

    @RequestMapping("/findOne")
    public SpecificationVo findOne(long id)
    {
          return  specificationService.findOne(id);
    }

    /**
     * 删除
     */

    @RequestMapping("/delete")
    public  R delete(Long[] ids)
    {
        try {
            specificationService.delete(ids);
            return new R(true, "成功");
        } catch (Exception e) {
            return new R(false, "失败");
        }
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList()
    {
        return specificationService.selectOptionList();
    }
}
