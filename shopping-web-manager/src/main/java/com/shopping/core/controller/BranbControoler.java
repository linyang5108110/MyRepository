package com.shopping.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shopping.core.pojo.good.Brand;
import com.shopping.core.service.BrandService;
import entity.PageResult;
import entity.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 品牌控制层
 */

@RestController
@RequestMapping("/brand")
public class BranbControoler {

    @Reference
    private BrandService brandService;

    //查询所品牌结果返回
    @RequestMapping("/findAll")
    public List<Brand> findAll() {
        return brandService.findAll();
    }

    @RequestMapping("/findPage")
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        return brandService.findPage(pageNum, pageSize);
    }


    @RequestMapping("/save")
    public R save(@RequestBody Brand brand) {
        try {
             brandService.save(brand);
            return new R(true, "添加成功");
        } catch (Exception e) {
            return new R(false, "添加失败");
        }
    }


    @RequestMapping("/findOne")
    public Brand findOne(Long id)
    {
       return brandService.findOne(id);
    }

    @RequestMapping("/update")
    public R update(@RequestBody  Brand brand)
    {
        try {
            brandService.update(brand);
            return new R(true, "修改成功");
        } catch (Exception e) {
            return new R(false, "修改失败");
        }
    }

    /**
     * 通过id删除一个或者多个
     * @param ids
     * @return
     */
    @RequestMapping("/deleteIds")
    public R delByIds(Long[] ids)
    {

        try {
            brandService.delByIds(ids);
            return new R(true, "删除成功");
        } catch (Exception e) {
            return new R(false, "删除失败");
        }
    }

    /**
     *条件分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/search")
//    public PageResult search(Integer pageNum, Integer pageSize,@RequestBody(required = false) Brand brand) {
    public PageResult search(Integer pageNum, Integer pageSize,@RequestBody Brand brand) {
        return brandService.search(pageNum,pageSize,brand);
    }

    /**
     * 查找所有 只要id 和  name
     * @return
     */
    @RequestMapping("/findList")
    public List<Map> findList()
    {
         return brandService.findList();
    }
}
