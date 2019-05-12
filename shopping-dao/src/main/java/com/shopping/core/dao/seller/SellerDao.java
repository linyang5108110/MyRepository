package com.shopping.core.dao.seller;

import com.shopping.core.pojo.seller.Seller;
import com.shopping.core.pojo.seller.SellerQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SellerDao {
    int countByExample(SellerQuery example);

    int deleteByExample(SellerQuery example);

    int deleteByPrimaryKey(String sellerId);

    int insert(Seller record);

    int insertSelective(Seller record);

    List<Seller> selectByExampleWithRowbounds(SellerQuery example, RowBounds rowBounds);

    List<Seller> selectByExample(SellerQuery example);

    Seller selectByPrimaryKey(String sellerId);

    int updateByExampleSelective(@Param("record") Seller record, @Param("example") SellerQuery example);

    int updateByExample(@Param("record") Seller record, @Param("example") SellerQuery example);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);
}