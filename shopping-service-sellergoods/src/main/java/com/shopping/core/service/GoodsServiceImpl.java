package com.shopping.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shopping.core.dao.good.BrandDao;
import com.shopping.core.dao.good.GoodsDao;
import com.shopping.core.dao.good.GoodsDescDao;
import com.shopping.core.dao.item.ItemCatDao;
import com.shopping.core.dao.item.ItemDao;
import com.shopping.core.dao.seller.SellerDao;
import com.shopping.core.pojo.good.Goods;
import com.shopping.core.pojo.good.GoodsQuery;
import com.shopping.core.pojo.item.Item;
import entity.GoodsVo;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.awt.image.TileObserver;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private  GoodsDao goodsDao;

    @Autowired
    private GoodsDescDao goodsDescDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private ItemCatDao itemCatDao;

    @Autowired
    private SellerDao sellerDao;

    @Autowired
    private BrandDao brandDao;

    @Override
    public void add(GoodsVo goodsVo) {
        //保存商品表
        //状态必须是0  为审核
        goodsVo.getGoods().setAuditStatus("0");
        goodsDao.insertSelective(goodsVo.getGoods());

        //保存商品详情 商品表和商品详情表两个同用一个id
        goodsVo.getGoodsDesc().setGoodsId(goodsVo.getGoods().getId());
        goodsDescDao.insertSelective(goodsVo.getGoodsDesc());

        //库存结果集

        List<Item> itemList = goodsVo.getItemList();
        if("1".equals(goodsVo.getGoods().getIsEnableSpec()))
        {
            for (Item item : itemList) {

                //拼装title {"机身内存":"16G","网络":"联通3G"}
                String title = goodsVo.getGoods().getGoodsName();
                String spec = item.getSpec();
                Map map = JSON.parseObject(spec, Map.class);
                Set<Map.Entry<String,String>> entries =  map.entrySet();
                for(Map.Entry<String,String> entry : entries)
                {
                    title += "" + entry.getValue();
                }
                //放入标题
                item.setTitle(title);

                //放入图片 第一张
                String itemImages = goodsVo.getGoodsDesc().getItemImages();
                List<Map> maps = JSON.parseArray(itemImages, Map.class);
                if(null != maps && maps.size() > 0)
                {
                    item.setImage((String) maps.get(0).get("url"));
                }

                //商品三级分类id
                item.setCategoryid(goodsVo.getGoods().getCategory3Id());
                //三级分类的名称
                item.setCategory(itemCatDao.selectByPrimaryKey(goodsVo.getGoods().getCategory3Id()).getName());

                //创建时间
                item.setCreateTime(new Date());
                //更新时间
                item.setUpdateTime(new Date());

                //设置goodsid
                item.setGoodsId(goodsVo.getGoods().getId());

                //设置商家id
                item.setSellerId(goodsVo.getGoods().getSellerId());

                //商家名称
                item.setBrand(sellerDao.selectByPrimaryKey(goodsVo.getGoods().getSellerId()).getNickName());
                //品牌名称
                item.setBrand(brandDao.selectByPrimaryKey(goodsVo.getGoods().getBrandId()).getName());

                //保存一条item
                itemDao.insertSelective(item);
            }
        }


    }

    @Override
    public PageResult search(Integer page, Integer rows, Goods goods) {
        PageHelper.startPage(page,rows);
        GoodsQuery query = new GoodsQuery();
        GoodsQuery.Criteria criteria = query.createCriteria();
         if(null !=  goods.getAuditStatus()  &&  !"".equals(goods.getAuditStatus()))
         {
             criteria.andAuditStatusEqualTo(goods.getAuditStatus());
         }
         if(null != goods.getGoodsName() && !"".equals(goods.getGoodsName()))
         {
             criteria.andGoodsNameLike("%"+goods.getGoodsName().trim()+"%");
         }
         //只能查询当前登陆商家的商品信息
         criteria.andSellerIdEqualTo(goods.getSellerId());
          //添加调价只能查询商品状态是上架状态的商品
         criteria.andIsDeleteIsNull();

         Page<Goods> p = (Page<Goods>) goodsDao.selectByExample(query);
         return new PageResult(p.getTotal(),p.getResult());
    }
}
