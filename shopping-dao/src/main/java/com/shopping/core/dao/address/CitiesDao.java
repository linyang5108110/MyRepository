package com.shopping.core.dao.address;

import com.shopping.core.pojo.address.Cities;
import com.shopping.core.pojo.address.CitiesQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface CitiesDao {
    int countByExample(CitiesQuery example);

    int deleteByExample(CitiesQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cities record);

    int insertSelective(Cities record);

    List<Cities> selectByExampleWithRowbounds(CitiesQuery example, RowBounds rowBounds);

    List<Cities> selectByExample(CitiesQuery example);

    Cities selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cities record, @Param("example") CitiesQuery example);

    int updateByExample(@Param("record") Cities record, @Param("example") CitiesQuery example);

    int updateByPrimaryKeySelective(Cities record);

    int updateByPrimaryKey(Cities record);
}