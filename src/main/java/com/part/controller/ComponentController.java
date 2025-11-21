package com.part.controller;

import com.part.data.entity.ComponentVO;
import com.part.service.ComponentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 零部件数据处理控制器
 */
@RestController
@RequestMapping("/api/components")
@RequiredArgsConstructor
@Tag(name = "零部件数据处理", description = "零部件数据抽取、导出、标注和模型训练接口")
public class ComponentController {

    private final ComponentService componentService;
    @GetMapping("/extract")
    @Operation(summary = "抽取零部件数据", description = "从数据库抽取零部件及子节点数据")
    public ResponseEntity<List<ComponentVO>> extractComponents(
            @Parameter(description = "开始时间")
            @RequestParam(required = false) String startDate,
            @Parameter(description = "结束时间")
            @RequestParam(required = false) String endDate,
            @Parameter(description = "父零部件名称模糊查询")
            @RequestParam(required = false) String parentNameLike) {

        List<ComponentVO> components = componentService.extractComponents(startDate, endDate, parentNameLike);
        return ResponseEntity.ok(components);
    }

    @GetMapping("/export")
    @Operation(summary = "导出零部件数据到Excel", description = "将抽取的零部件数据导出为Excel文件")
    public void exportToExcel(
            HttpServletResponse response,
            @Parameter(description = "开始时间")
            @RequestParam(required = false) String startDate,
            @Parameter(description = "结束时间")
            @RequestParam(required = false) String endDate,
            @Parameter(description = "父零部件名称模糊查询")
            @RequestParam(required = false) String parentNameLike) {

        componentService.exportToExcel(response, startDate, endDate, parentNameLike);
    }

    @PostMapping("/import-annotated")
    @Operation(summary = "导入标注数据", description = "导入人工标注后的Excel数据")
    public ResponseEntity<String> importAnnotatedData(
            @Parameter(description = "包含标注数据的Excel文件")
            @RequestParam("file") MultipartFile file) {
        
        try {
            // 实际项目中应先保存文件再处理
            String filePath = saveFileTemp(file);
            int count = componentService.importAnnotatedData(filePath);
            return ResponseEntity.ok("成功导入 " + count + " 条标注数据");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("导入失败: " + e.getMessage());
        }
    }

    @PostMapping("/train-model")
    @Operation(summary = "训练分类模型", description = "使用标注数据训练分类模型")
    public ResponseEntity<String> trainModel() {
        String result = componentService.trainModel();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/predict")
    @Operation(summary = "预测分类", description = "使用训练好的模型预测零部件分类")
    public ResponseEntity<List<ComponentVO>> predictClassification(
            @Parameter(description = "待预测的零部件数据")
            @RequestBody List<ComponentVO> components) {
        
        List<ComponentVO> result = componentService.predictClassification(components);
        return ResponseEntity.ok(result);
    }

    // 在ComponentController.java中新增以下代码
    @PostMapping(value = "/import-raw", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "导入原始零部件数据", description = "将导出的ComponentVO数据导入到DdRawComponentVO表")
    public ResponseEntity<String> importRawData(
            @Parameter(description = "原始零部件数据Excel文件（从/export接口导出）")
            @RequestPart("file") MultipartFile file) {

        try {
            // 保存上传的临时文件
            String filePath = saveFileTemp(file);
            int count = componentService.importRawData(filePath);
            return ResponseEntity.ok("成功导入 " + count + " 条原始零部件数据到DdRawComponentVO表");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("导入失败: " + e.getMessage());
        }
    }
    // 保存上传的临时文件
    private String saveFileTemp(MultipartFile file) throws Exception {
        // 实际实现应保存到临时目录
        String tempDir = System.getProperty("java.io.tmpdir");
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        java.io.File tempFile = new java.io.File(tempDir, fileName);
        file.transferTo(tempFile);
        return tempFile.getAbsolutePath();
    }
}
    