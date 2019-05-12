package com.shopping.core.dao.specification;

import com.shopping.core.pojo.specification.SpecificationOption;
import com.shopping.core.pojo.specification.SpecificationOptionQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SpecificationOptionDao {
    int countByExample(SpecificationOptionQuery example);

    int deleteByExample(SpecificationOptionQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(SpecificationOption record);

    int insertSelective(SpecificationOption record);

    List<SpecificationOption> selectByExampleWithRowbounds(SpecificationOptionQuery example, RowBounds rowBounds);

    List<SpecificationOption> selectByExample(SpecificationOptionQuery example);

    SpecificationOption selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SpecificationOption record, @Param("example") SpecificationOptionQuery example);

    int updateByExample(@Param("record") SpecificationOption record, @Param("example") SpecificationOptionQuery example);

    int updateByPrimaryKeySelective(SpecificationOption record);

    int updateByPrimaryKey(SpecificationOption record);
}