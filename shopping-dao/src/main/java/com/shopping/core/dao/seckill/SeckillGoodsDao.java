package com.shopping.core.dao.seckill;

import com.shopping.core.pojo.seckill.SeckillGoods;
import com.shopping.core.pojo.seckill.SeckillGoodsQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SeckillGoodsDao {
    int countByExample(SeckillGoodsQuery example);

    int deleteByExample(SeckillGoodsQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(SeckillGoods record);

    int insertSelective(SeckillGoods record);

    List<SeckillGoods> selectByExampleWithRowbounds(SeckillGoodsQuery example, RowBounds rowBounds);

    List<SeckillGoods> selectByExample(SeckillGoodsQuery example);

    SeckillGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SeckillGoods record, @Param("example") SeckillGoodsQuery example);

    int updateByExample(@Param("record") SeckillGoods record, @Param("example") SeckillGoodsQuery example);

    int updateByPrimaryKeySelective(SeckillGoods record);

    int updateByPrimaryKey(SeckillGoods record);
}