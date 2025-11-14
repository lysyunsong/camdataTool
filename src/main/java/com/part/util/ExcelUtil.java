package com.part.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Excel处理工具类
 */
@Slf4j
public class ExcelUtil {

    /**
     * 导出Excel文件
     * @param response HTTP响应对象
     * @param data 导出的数据列表
     * @param clazz 数据类型
     * @param fileName 文件名
     * @param <T> 泛型类型
     */
    public static <T> void exportExcel(HttpServletResponse response, List<T> data, Class<T> clazz, String fileName) {
        try {
            // 设置响应头
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("UTF-8");
            
            // 处理文件名中文乱码
            String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name())
                    .replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", 
                    "attachment;filename*=UTF-8''" + encodedFileName + ".xlsx");
            
            // 写入Excel
            EasyExcel.write(response.getOutputStream(), clazz)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()) // 自动适应列宽
                    .sheet("数据")
                    .doWrite(data);
                    
            log.info("Excel导出成功，文件名: {}, 数据量: {}", fileName, data.size());
        } catch (IOException e) {
            log.error("Excel导出失败", e);
            throw new RuntimeException("Excel导出失败", e);
        }
    }
}
    