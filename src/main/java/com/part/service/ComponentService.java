package com.part.service;

import com.part.data.entity.ComponentVO;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 零部件数据处理服务接口
 */
public interface ComponentService {

    /**
     * 抽取零部件及子节点数据
     * @param limit 抽取数量限制
     * @param parentNameLike 父零部件名称模糊查询
     * @return 零部件数据列表
     */
    List<ComponentVO> extractComponents(String startDate, String endDate, String parentNameLike);

    /**
     * 导出零部件数据到Excel
     * @param response HTTP响应对象
     * @param limit 导出数量限制
     * @param parentNameLike 父零部件名称模糊查询
     */
    void exportToExcel(HttpServletResponse response, String startDate, String endDate, String parentNameLike);

    /**
     * 导入标注后的Excel数据
     * @param filePath Excel文件路径
     * @return 导入成功数量
     */
    int importAnnotatedData(String filePath);

    /**
     * 保存标注数据到数据库
     * @param components 带标注的零部件数据列表
     * @return 保存成功数量
     */
    int saveAnnotatedData(List<ComponentVO> components);

    /**
     * 获取用于模型训练的标注数据
     * @param limit 数据量限制
     * @return 标注数据列表
     */
    List<ComponentVO> getTrainingData(String startDate, String endDate);

    /**
     * 训练分类模型
     * @return 训练结果
     */
    String trainModel();

    /**
     * 使用模型预测分类
     * @param components 待预测的零部件数据
     * @return 带预测结果的数据列表
     */
    List<ComponentVO> predictClassification(List<ComponentVO> components);

    int importRawData(String filePath);
}
    