# 零部件分类系统

## 项目概述
零部件分类系统是一个基于Spring Boot和LightGBM的机器学习分类系统，能够从数据库抽取零部件数据，通过人工标注训练分类模型，并使用模型对新数据进行自动分类，提高零部件数据管理效率。

## 技术栈
- 后端框架：Spring Boot 3.2.0
- 数据访问：MyBatis + Oracle数据库
- 机器学习：LightGBM
- 文档：Swagger/OpenAPI
- 数据处理：EasyExcel
- 开发语言：Java 21

## 系统功能
1. 零部件数据抽取：从数据库提取零部件及子节点数据
2. 数据导出导入：支持Excel格式的数据导出和标注数据导入
3. 模型训练：使用标注数据训练LightGBM分类模型
4. 分类预测：使用训练好的模型对新数据进行自动分类

## 环境配置
1. JDK 21
2. Maven 3.6+
3. Oracle数据库
4. LightGBM依赖环境

## 部署步骤
1. 克隆代码仓库
2. 修改`application.yml`配置数据库连接信息
3. 执行`mvn clean package`构建项目
4. 运行`java -jar target/part-classification-system-0.0.1-SNAPSHOT.jar`启动服务
5. 访问`http://localhost:8080/part-system/swagger-ui.html`查看API文档

## 使用流程
1. 调用`/api/components/extract`接口抽取数据
2. 调用`/api/components/export`接口导出数据进行人工标注
3. 调用`/api/components/import-annotated`接口导入标注数据
4. 调用`/api/components/train-model`接口训练分类模型
5. 调用`/api/components/predict`接口对新数据进行分类预测