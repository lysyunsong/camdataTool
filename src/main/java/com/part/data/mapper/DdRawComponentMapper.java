// com/part/data/mapper/DdRawComponentMapper.java
package com.part.data.mapper;

import com.part.data.entity.DdRawComponentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DdRawComponentMapper {
    int batchInsert(@Param("list") List<DdRawComponentVO> rawComponents);
    long count();
    List<DdRawComponentVO> selectByPage(@Param("offset") int offset, @Param("limit") int limit);
}