package com.part.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.part.data.entity.ComponentVO;
import com.part.data.mapper.ComponentMapper;
import com.part.service.ComponentService;
import com.part.util.ExcelUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * 零部件数据处理服务实现
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ComponentServiceImpl implements ComponentService {

    private final ComponentMapper componentMapper;

    @Override
    public List<ComponentVO> extractComponents(String startDate, String endDate, String parentNameLike) {
        log.info("抽取零部件数据，开始时间: {}, 结束时间: {}, 父名称模糊查询: {}", startDate, endDate, parentNameLike);
        return componentMapper.extractComponentWithSubnode(startDate, endDate, parentNameLike);
    }

    @Override
    public void exportToExcel(HttpServletResponse response, String startDate, String endDate, String parentNameLike) {
        log.info("导出零部件数据到Excel，开始时间: {}, 结束时间: {}, 父名称模糊查询: {}", startDate, endDate, parentNameLike);
        List<ComponentVO> components = extractComponents(startDate, endDate, parentNameLike);
        ExcelUtil.exportExcel(response, components, ComponentVO.class, "零部件数据_" + System.currentTimeMillis());
    }

    @Override
    public int importAnnotatedData(String filePath) {
        log.info("导入标注后的Excel数据，文件路径: {}", filePath);
        try {
            File file = ResourceUtils.getFile(filePath);
            final int[] count = {0};
            
            EasyExcel.read(file, ComponentVO.class, new PageReadListener<ComponentVO>(dataList -> {
                count[0] += saveAnnotatedData(dataList);
                log.info("已导入 {} 条标注数据", count[0]);
            })).sheet().doRead();
            
            return count[0];
        } catch (FileNotFoundException e) {
            log.error("导入文件不存在", e);
            throw new RuntimeException("导入文件不存在", e);
        }
    }

    @Override
    @Transactional
    public int saveAnnotatedData(List<ComponentVO> components) {
        if (components == null || components.isEmpty()) {
            return 0;
        }
        // 过滤掉没有标注的数据
        List<ComponentVO> validData = components.stream()
                .filter(c -> c.getManualLabel() != null && !c.getManualLabel().trim().isEmpty())
                .toList();
                
        if (validData.isEmpty()) {
            return 0;
        }
        
        return componentMapper.batchInsertAnnotatedData(validData);
    }

    @Override
    public List<ComponentVO> getTrainingData(String startDate, String endDate) {
        log.info("获取用于模型训练的标注数据，开始时间: {}, 结束时间: {}", startDate, endDate);
        return extractComponents(startDate, endDate, null);
    }

    @Override
    public String trainModel() {
        log.info("开始训练分类模型");
        
        // 1. 获取训练数据
        List<ComponentVO> trainingData = getTrainingData("2009-11-04 00:00:00", "2009-11-04 23:59:59");
        if (trainingData.size() < 100) {
            return "训练数据不足，至少需要100条标注数据";
        }
        
        // 2. 特征工程处理
        // 这里只是框架，实际需要根据业务提取特征
        
        // 3. 模型训练
        // 集成LightGBM或其他机器学习库进行模型训练
        try {
            // 模拟模型训练过程
            Thread.sleep(3000);
            
            // 4. 保存模型
            // 将训练好的模型保存到文件系统
            
            log.info("模型训练完成，训练样本数: {}", trainingData.size());
            return "模型训练成功，训练样本数: " + trainingData.size();
        } catch (Exception e) {
            log.error("模型训练失败", e);
            return "模型训练失败: " + e.getMessage();
        }
    }

    @Override
    public List<ComponentVO> predictClassification(List<ComponentVO> components) {
        log.info("对 {} 条数据进行分类预测", components.size());
        
        // 实际项目中应该加载训练好的模型进行预测
        // 这里简单模拟预测结果
        components.forEach(component -> {
            // 简单规则模拟预测
            if (component.getParentName() != null && component.getParentName().contains("电池")) {
                component.setPredictedLabel("电池组件");
                component.setConfidence(0.85);
            } else if (component.getParentName() != null && component.getParentName().contains("外壳")) {
                component.setPredictedLabel("结构件");
                component.setConfidence(0.92);
            } else {
                component.setPredictedLabel("其他");
                component.setConfidence(0.6);
            }
        });
        
        return components;
    }
}
    