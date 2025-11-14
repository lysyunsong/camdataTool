package com.part.data.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 零部件及子节点数据VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComponentVO {
    // 父零部件信息
    @ExcelProperty(value = "零部件结构ID", index = 0)
    @ColumnWidth(20)
    private String parentStructId;
    
    @ExcelProperty(value = "零部件CKID", index = 1)
    @ColumnWidth(20)
    private String parentCkid;
    
    @ExcelProperty(value = "零部件名称", index = 2)
    @ColumnWidth(25)
    private String parentName;

    @ExcelProperty(value = "零部件英文名称", index = 3)
    @ColumnWidth(25)
    private String parentNameEn;
    
    @ExcelProperty(value = "零部件代号", index = 4)
    @ColumnWidth(20)
    private String parentSymbol;
    
    @ExcelProperty(value = "零部件重量", index = 5)
    @ColumnWidth(15)
    private BigDecimal parentWeight;

    // 子节点信息
    @ExcelProperty(value = "子节点类型", index = 6)
    @ColumnWidth(15)
    private String subnodeType;
    
    @ExcelProperty(value = "子节点CKID", index = 7)
    @ColumnWidth(20)
    private String subnodeCkid;
    
    @ExcelProperty(value = "子节点名称", index = 8)
    @ColumnWidth(25)
    private String subnodeName;

    @ExcelProperty(value = "子节点英文名称", index = 9)
    @ColumnWidth(25)
    private String subnodeNameEn;
    
    @ExcelProperty(value = "子节点代号", index = 10)
    @ColumnWidth(20)
    private String subnodeSymbol;
    
    @ExcelProperty(value = "子节点重量", index = 11)
    @ColumnWidth(15)
    private BigDecimal subnodeWeight;

    @ExcelProperty(value = "子节点数量", index = 12)
    @ColumnWidth(15)
    private Integer subnodeQuantity;
    
    @ExcelProperty(value = "子节点结构ID", index = 13)
    @ColumnWidth(20)
    private String subnodeStructId;
    
    // 用于后期标注的字段
    @ExcelProperty(value = "人工标注类别", index = 14)
    @ColumnWidth(20)
    private String manualLabel;
    
    // 用于模型预测的字段
    @ExcelProperty(value = "模型预测类别", index = 15)
    @ColumnWidth(20)
    private String predictedLabel;
    
    @ExcelProperty(value = "预测置信度", index = 16)
    @ColumnWidth(15)
    private Double confidence;
}
    