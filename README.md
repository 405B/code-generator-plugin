<p align="left">
 <a href="README_CN.md">简体中文文档</a>
</p>

# code-generator-plugin

Database (including MySql and Oracle) to the code automatically generated, you can customize the build framework, such as mybatis, springboot, spring and so on. You can also customize your own Velocity template, generate the appropriate code.



## Technology used

1. JDK1.8
2. JDK Toolkit（mysql-connector-java）
3. Velocity template tool

## Realize the program

Read the model (library, table) metadata information, generate the appropriate code.

This tool uses mysql as the metadata source, reads the metadata information using JDBC tools, and then uses the Velocity Template tool to produce the project code.

## Quick start

The sample program will generate a project maven project based on spring-boot, mybatis, containing the basic CURD operation code.

- Step 1: Import this tool project into IDE (Intellij IDEA or Eclipse)

- Step 2: Modify the configuration file  `src/main/resources/config.properties`


| Property name              | description                              |
| :------------------------- | :--------------------------------------- |
| db.url                     | Database connection URL                  |
| db.driver                  | Database driven                          |
| db.username                | database username                        |
| db.password                | Database password                        |
| db.type                    | Currently only supports MYSQL (self-expandable) |
| table.prefix               | Table prefix that need to generate code  |
| separate.modle             | Whether to separate the module, 0: no, 1: yes, for example: sys_user table, the packet path will increase a layer of sys |
| project.name               | Project name to generate                 |
| base.package               | Basic package name                       |
| generate.template.location | Template package (self-expandable), corresponding to the resources / template template directory |
| gen.controller             | Whether to generate action (controler) layer, 0: false, 1: true |
| gen.service                | Whether to generate business (service) layer, 0: false, 1: true |
| gen.dao                    | Whether to generate dao layer, 0: false, 1: true |
| gen.pojo                   | Whether to generate pojo (entry) layer 0: false, 1: true |
| gen.resource               | Whether to generate resource file, 0: false, 1: true |
| class.suffix.controller    | Control layer suffix, for example: Controller will generate xx.xx.ctl.xxxController.java |
| class.suffix.service       | Service layer suffix, for example: Service will generate xx.xx.service.xxxService.java |
| class.suffix.dao           | Map layer suffix, for example: Mapper will generate xx.xx.mapper.xxxMapper.java |
| class.suffix.pojo          | entity layer suffix, for example: Pojo will generate xx.xx.po.xxxPojo.java |
| output.dir                 | Code output directory                    |

- Step 3: the implementation of `cn.yastarter.generator.core.Main`， began to generate code
- Step 4: Go to the directory specified by `output.dir` and import the code into your project project


## Generate the result

```
├── output                                 // Generate the result input directory, corresponding to: $ {output.dir}
├── src   
│   ├── main                               
│   │   ├── java                           
│   │   │   └── com.oovever.test           // Base package name, corresponding to: $ {base.package}
│   │   │       ├── modle1                 // When the module name, $ {separate.modle}, is set to 1, it is possible to generate a module layer
│   │   │       │   ├── controller         // Control layer package, corresponding to: $ {class.suffix.controller}
│   │   │       │   ├── service            // Business layer package, corresponding to: $ {class.suffix.service}
│   │   │       │   ├── dao                //Mapping layer package, corresponding to: $ {class.suffix.dao}
│   │   │       │   └── pojo               // entity layer package, corresponding to: $ {class.suffix.pojo}
│   │   │       └── TestApplication.java   // spring boot start the program
│   │   └── resources                      
│   │       ├── mapper                     // mybatis mapping package
│   │       ├── application.properties     // Basic resource documents
│   │       ├── application-dev.properties // Development environment resource file
│   │       ├── logback-spring.xml             // logback config file
│   │       └── mybatis.xml                // mybatis configuration file
│   └── test                               // Unit test package
└── pom.xml                                // maven package file

```
## How to expand

1. Custom templates, please add your own project template according to your own project framework. For the variables in the template, please refer to the template variables in` template / test` template package. For definitions of variables, please refer to the definition in `cn.yastarter.generator.core.bean package`
2. If you want to increase support for other databases, in two steps
   - Please extend` cn.yastarter.generator.core.generator.reader.AbstractDbReader` class, please refer to `MysqlDbReader`, and add support for it in` DbReaderFactory`
   - Please achieve `cn.yastarter.generator.core.generator.dbGenerator. DbGenerator` interface, refer to `MysqlGenerator`, and increase its support in `DbGeneratorFactory`
3. If you want to increase support for other data sources (non-database), according to the entire generator architecture, to achieve their own reader and generator

