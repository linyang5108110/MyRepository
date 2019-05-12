package com.shopping.core.dao.good;

import com.shopping.core.pojo.good.Goods;
import com.shopping.core.pojo.good.GoodsQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GoodsDao {
    int countByExample(GoodsQuery example);

    int deleteByExample(GoodsQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExampleWithRowbounds(GoodsQuery example, RowBounds rowBounds);

    List<Goods> selectByExample(GoodsQuery example);

    Goods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsQuery example);

    int updateByExample(@Param("record") Goods record, @Param("example") GoodsQuery example);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}