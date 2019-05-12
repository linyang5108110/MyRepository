package com.shopping.core.dao.ad;

import com.shopping.core.pojo.ad.Content;
import com.shopping.core.pojo.ad.ContentQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ContentDao {
    int countByExample(ContentQuery example);

    int deleteByExample(ContentQuery example);

    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    List<Content> selectByExampleWithRowbounds(ContentQuery example, RowBounds rowBounds);

    List<Content> selectByExample(ContentQuery example);

    Content selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Content record, @Param("example") ContentQuery example);

    int updateByExample(@Param("record") Content record, @Param("example") ContentQuery example);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);
}