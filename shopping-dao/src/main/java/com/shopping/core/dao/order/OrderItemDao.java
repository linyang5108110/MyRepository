package com.shopping.core.dao.order;

import com.shopping.core.pojo.order.OrderItem;
import com.shopping.core.pojo.order.OrderItemQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface OrderItemDao {
    int countByExample(OrderItemQuery example);

    int deleteByExample(OrderItemQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    List<OrderItem> selectByExampleWithRowbounds(OrderItemQuery example, RowBounds rowBounds);

    List<OrderItem> selectByExample(OrderItemQuery example);

    OrderItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderItem record, @Param("example") OrderItemQuery example);

    int updateByExample(@Param("record") OrderItem record, @Param("example") OrderItemQuery example);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);
}