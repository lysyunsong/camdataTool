package com.part.data.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 原始零部件数据VO（对应DD_RAW_COMPONENT表，用于数据清洗前的原始数据存储）
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DdRawComponentVO {

    @ExcelProperty(value = "自增主键", index = 0)
    @ColumnWidth(20)
    private Long id; // 对应ID（SEQ_DD_RAW_COMPONENT生成）

    // 父零部件信息
    @ExcelProperty(value = "父结构ID", index = 1)
    @ColumnWidth(20)
    private String parentStructId; // 父结构ID（pstr.CSID）

    @ExcelProperty(value = "父CKID", index = 2)
    @ColumnWidth(20)
    private String parentCkid; // 父CKID（pstr.CCKID）

    @ExcelProperty(value = "父名称", index = 3)
    @ColumnWidth(25)
    private String parentName; // 父名称（comp_parent.CNAME）

    @ExcelProperty(value = "父英文名称", index = 4)
    @ColumnWidth(25)
    private String parentNameEn; // 父英文名称（comp_parent.CEN_NAME）

    @ExcelProperty(value = "父代号", index = 5)
    @ColumnWidth(20)
    private String parentSymbol; // 父代号（comp_parent.CSYMBOL）

    @ExcelProperty(value = "父重量（原始）", index = 6)
    @ColumnWidth(15)
    private String parentWeight; // 父重量（原始格式，含单位）

    // 子节点信息
    @ExcelProperty(value = "子节点类型", index = 7)
    @ColumnWidth(15)
    private String subnodeType; // 子节点类型（1=零部件，2=半成品，3=材料）

    @ExcelProperty(value = "子节点CKID", index = 8)
    @ColumnWidth(20)
    private String subnodeCkid; // 子节点CKID（cstr.CCKID）

    @ExcelProperty(value = "子节点名称", index = 9)
    @ColumnWidth(25)
    private String subnodeName; // 子节点名称

    @ExcelProperty(value = "子节点英文名称", index = 10)
    @ColumnWidth(25)
    private String subnodeNameEn; // 子节点英文名称

    @ExcelProperty(value = "子节点代号", index = 11)
    @ColumnWidth(20)
    private String subnodeSymbol; // 子节点代号

    @ExcelProperty(value = "子节点重量（原始）", index = 12)
    @ColumnWidth(15)
    private String subnodeWeight; // 子节点重量（原始格式，含单位）

    @ExcelProperty(value = "子节点数量", index = 13)
    @ColumnWidth(15)
    private Integer subnodeQuantity; // 子节点数量（cstr.CQUANTITY）

    @ExcelProperty(value = "子节点结构ID", index = 14)
    @ColumnWidth(20)
    private String subnodeStructId; // 子节点结构ID（cstr.CSID）

    // 扩展字段
    @ExcelProperty(value = "人工标注类别", index = 15)
    @ColumnWidth(20)
    private String manualLabel; // 人工标注类别（初始为空）

    @ExcelProperty(value = "数据导入时间", index = 16)
    @ColumnWidth(20)
    private Date createTime; // 数据导入时间（默认SYSDATE）

    @ExcelProperty(value = "导入批次号", index = 17)
    @ColumnWidth(20)
    private String importBatch; // 导入批次号（区分多批数据）
}