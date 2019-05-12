package com.shopping.core.dao.seckill;

import com.shopping.core.pojo.seckill.SeckillOrder;
import com.shopping.core.pojo.seckill.SeckillOrderQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SeckillOrderDao {
    int countByExample(SeckillOrderQuery example);

    int deleteByExample(SeckillOrderQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(SeckillOrder record);

    int insertSelective(SeckillOrder record);

    List<SeckillOrder> selectByExampleWithRowbounds(SeckillOrderQuery example, RowBounds rowBounds);

    List<SeckillOrder> selectByExample(SeckillOrderQuery example);

    SeckillOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SeckillOrder record, @Param("example") SeckillOrderQuery example);

    int updateByExample(@Param("record") SeckillOrder record, @Param("example") SeckillOrderQuery example);

    int updateByPrimaryKeySelective(SeckillOrder record);

    int updateByPrimaryKey(SeckillOrder record);
}