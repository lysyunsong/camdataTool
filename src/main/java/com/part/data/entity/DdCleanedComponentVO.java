package com.part.data.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 清洗后零部件数据VO（对应DD_CLEANED_COMPONENT表，用于存储清洗结果）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DdCleanedComponentVO {

    @ExcelProperty(value = "清洗记录ID", index = 0)
    @ColumnWidth(20)
    private Long id; // 清洗记录ID（SEQ_DD_CLEANED_COMPONENT生成）

    @ExcelProperty(value = "原始数据ID", index = 1)
    @ColumnWidth(20)
    private Long rawId; // 关联原始数据ID（DD_RAW_COMPONENT.ID）

    // 父零部件清洗后信息
    @ExcelProperty(value = "清洗后父名称", index = 2)
    @ColumnWidth(25)
    private String parentNameCleaned; // 清洗后的父名称

    @ExcelProperty(value = "清洗后父英文名称", index = 3)
    @ColumnWidth(25)
    private String parentNameEnCleaned; // 清洗后的父英文名称

    @ExcelProperty(value = "清洗后父代号", index = 4)
    @ColumnWidth(20)
    private String parentSymbolCleaned; // 清洗后的父代号

    @ExcelProperty(value = "归一化父重量（kg）", index = 5)
    @ColumnWidth(15)
    private BigDecimal parentWeightCleaned; // 归一化父重量（kg）

    // 子节点清洗后信息
    @ExcelProperty(value = "标准化子节点类型", index = 6)
    @ColumnWidth(15)
    private String subnodeTypeCleaned; // 标准化子节点类型（如“零部件”“材料”）

    @ExcelProperty(value = "清洗后子节点名称", index = 7)
    @ColumnWidth(25)
    private String subnodeNameCleaned; // 清洗后的子节点名称

    @ExcelProperty(value = "清洗后子节点英文名称", index = 8)
    @ColumnWidth(25)
    private String subnodeNameEnCleaned; // 清洗后的子节点英文名称

    @ExcelProperty(value = "清洗后子节点代号", index = 9)
    @ColumnWidth(20)
    private String subnodeSymbolCleaned; // 清洗后的子节点代号

    @ExcelProperty(value = "归一化子节点重量（kg）", index = 10)
    @ColumnWidth(15)
    private BigDecimal subnodeWeightCleaned; // 归一化子节点重量（kg）

    @ExcelProperty(value = "清洗后子节点数量", index = 11)
    @ColumnWidth(15)
    private Integer subnodeQuantityCleaned; // 清洗后的子节点数量（处理异常值）

    // 模型相关字段
    @ExcelProperty(value = "人工标注类别", index = 12)
    @ColumnWidth(20)
    private String manualLabel; // 人工标注类别（同步自原始表）

    @ExcelProperty(value = "模型预测类别", index = 13)
    @ColumnWidth(20)
    private String predictedLabel; // 模型预测类别

    @ExcelProperty(value = "预测置信度", index = 14)
    @ColumnWidth(15)
    private Double confidence; // 预测置信度

    // 清洗状态字段
    @ExcelProperty(value = "清洗状态", index = 15)
    @ColumnWidth(15)
    private String cleanStatus; // success/pending/error

    @ExcelProperty(value = "清洗时间", index = 16)
    @ColumnWidth(20)
    private Date cleanTime; // 清洗时间（默认SYSDATE）
}