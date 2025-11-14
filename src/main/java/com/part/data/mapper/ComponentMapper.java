package com.part.data.mapper;

import com.part.data.entity.ComponentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 零部件数据访问接口
 */
@Mapper
public interface ComponentMapper {
    /**
     * 抽取零部件及一级子节点数据
     * @param limit 抽取数量限制
     * @param parentNameLike 父零部件名称模糊匹配
     * @return 零部件-子节点关联数据列表
     */
    List<ComponentVO> extractComponentWithSubnode(@Param("startDate") String startDate,
                                                  @Param("endDate") String endDate,
                                                  @Param("parentNameLike") String parentNameLike);
    
    /**
     * 获取总记录数
     * @param parentNameLike 父零部件名称模糊匹配
     * @return 总记录数
     */
    long countComponents(@Param("parentNameLike") String parentNameLike);

    /**
     * 批量插入标注数据，用于存储人工标注结果
     * @param components 带标注的零部件数据列表
     * @return 插入数量
     */
    int batchInsertAnnotatedData(@Param("list") List<ComponentVO> components);
}
    