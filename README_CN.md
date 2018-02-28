<p align="left">
 <a href="README.md">English  Document</a>
</p>

## 代码生成器

根据数据库（包括MySql和Oracle）定义的表结构,生成基于 spring-boot、mybatis，包含基本 `CURD` 操作代码的工程 `maven` 工程，从而减少程序开发中重复代码的撰写，提高开发效率。

## 所用技术

1. JDK1.8
2. JDBC工具包（mysql-connector-java）
3. velocity模板工具（也可扩展freemarker模板）

## 实现方案

此工具使用mysql（也可扩展自己的Oracle）作为元数据来源，利用JDBC工具读取数据库表中的基本元数据信息，然后使用 `velocity` 模板工具生产工程代码。

## 快速入门

示例程序，将生成一个基于 spring-boot、mybatis，包含基本 `CURD` 操作代码的工程 `maven` 工程。

- 第一步：导入此工具工程到 IDE(Intellij IDEA 或者Eclipse)
- 第二步：修改配置文件 `src/main/resources/config.properties`

| 属性名                        | 描述                                       |
| :------------------------- | :--------------------------------------- |
| db.url                     | 数据库连接URL                                 |
| db.driver                  | 数据库驱动                                    |
| db.username                | 数据库用户名                                   |
| db.password                | 数据库密码                                    |
| db.type                    | 目前仅支持MYSQL（可自行扩展)                        |
| table.prefix               | 需要生成代码的表的前缀                              |
| separate.modle             | 是否分离模块，0:no,1:yes，例如：sys_user 表，包路径上会增加一层 `sys` |
| project.name               | 要生成的工程名                                  |
| base.package               | 基础包名                                     |
| generate.template.location | 模板包（可自行扩展），对应 resources/template下的模板目录   |
| gen.controller             | 是否生成 action（controler）层，0:false，1:true   |
| gen.service                | 是否生成 business（service）层，0:false，1:true   |
| gen.dao                    | 是否生成 dao层 ，0:false，1:true                |
| gen.pojo                   | 是否生成 pojo（entry）层  0:false，1:true        |
| gen.resource               | 是否生成 资源文件 ，0:false，1:true                |
| class.suffix.controller    | 控制层后缀，例如：Control将会生成 xx.xx.ctl.xxxControl.java |
| class.suffix.service       | 业务层后缀，例如：Service 将会生成 xx.xx.service.xxxService.java |
| class.suffix.dao           | 映射层后缀，例如：Mapper 将会生成 xx.xx.mapper.xxxMapper.java |
| class.suffix.pojo          | 实体层后缀，例如：Pojo 将会生成 xx.xx.po.xxxPojo.java |
| output.dir                 | 代码输出目录                                   |

- 第三步：执行 `cn.yastarter.generator.core.Main`，开始生成代码
- 第四步：到 `output.dir` 指定的目录，将代码导入到你的项目工程

## 生成结果

```
├── output                                 // 生成结果输入目录，对应：${output.dir}
├── src                                    
│   ├── main                               
│   │   ├── java                           
│   │   │   └── self.aub.product.cgt       // 基础包名，对应：${base.package}
│   │   │       ├── modle1                 // 模块名，${separate.modle} 设置为 1 时，有可能产生模块层
│   │   │       │   ├── controller         // 控制层包，对应：${class.suffix.controller}
│   │   │       │   ├── service            // 业务层包，对应：${class.suffix.service}
│   │   │       │   ├── dao                // 映射层包，对应：${class.suffix.dao}
│   │   │       │   └── pojo               // 实体层包，对应：${class.suffix.pojo}
│   │   │       └── TestApplication.java   // spring boot启动程序
│   │   └── resources                      
│   │       ├── mapper                     // mybatis 映射文件包
│   │       ├── application.properties     // 基础资源文件
│   │       ├── application-dev.properties // 开发环境资源文件
│   │       ├── logback-spring.xml         // logback配置文件
│   │       └── mybatis.xml                // mybatis 配置文件
│   └── test                               // 测试程序所在文件夹
└── pom.xml                                // maven 打包文件

```
## 如何扩展

1. 自定义模板，请根据自己的工程框架，添加自己的工程模板，模板中的变量请参考 `template/test`模板包中的模板变量，变量含义请参考 `cn.yastarter.generator.core.bean` 包中的定义
2. 如果想增加对其他数据库的支持， 分两步
   - 请扩展 `cn.yastarter.generator.core.generator.reader.AbstractDbReader` 类，请参考 `MysqlDbReader`，并在 `DbReaderFactory` 中增加对其的支持
   - 请实现 `cn.yastarter.generator.core.generator.dbGenerator. DbGenerator ` 接口，可参考 `MysqlGenerator` ，并在 `DbGeneratorFactory` 中增加对其的支持
3. 如果想增加对其他数据源（非数据库）的支持，根据整个生成器架构，自行实现 `reader` 和 `generator`