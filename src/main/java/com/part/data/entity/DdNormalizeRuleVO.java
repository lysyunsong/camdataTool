package com.part.data.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据标准化规则VO（对应DD_NORMALIZE_RULE表，用于存储清洗规则）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DdNormalizeRuleVO {

    @ExcelProperty(value = "规则ID", index = 0)
    @ColumnWidth(10)
    private Integer id; // 规则ID（SEQ_DD_NORMALIZE_RULE生成）

    @ExcelProperty(value = "规则类型", index = 1)
    @ColumnWidth(15)
    private String ruleType; // 规则类型：name/name_en/symbol/type/weight

    @ExcelProperty(value = "适用原始字段", index = 2)
    @ColumnWidth(20)
    private String sourceField; // 适用字段：parent_name/subnode_name等

    @ExcelProperty(value = "原始值", index = 3)
    @ColumnWidth(20)
    private String sourceStr; // 待替换的原始值（如“Battery”“1.2kg”）

    @ExcelProperty(value = "目标值", index = 4)
    @ColumnWidth(20)
    private String targetStr; // 替换后的目标值（如“电池”“1.2”）

    @ExcelProperty(value = "优先级", index = 5)
    @ColumnWidth(10)
    private Integer priority; // 优先级（数字越大越先执行，默认1）

    @ExcelProperty(value = "是否启用", index = 6)
    @ColumnWidth(10)
    private Integer enable; // 1=启用，0=禁用（默认1）

    @ExcelProperty(value = "规则说明", index = 7)
    @ColumnWidth(30)
    private String remark; // 规则说明（如“子节点名称中的Battery替换为电池”）
}