package com.part.data.mapper;

import com.part.data.entity.DdComponentClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DdComponentClassMapper {
    // 批量插入分类数据
    int batchInsert(@Param("list") List<DdComponentClass> classes);

    // 查询所有分类（用于后续关联）
    List<DdComponentClass> selectAll();
}