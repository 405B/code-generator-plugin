package cn.yastarter.generator.core;

import cn.yastarter.generator.core.bean.Table;
import cn.yastarter.generator.core.config.GeneratorConfig;
import cn.yastarter.generator.core.config.VelocityConfig;
import cn.yastarter.generator.core.generator.codeGenerate.ApplicationGenerator;
import cn.yastarter.generator.core.generator.codeGenerate.resource.LogConfigGenerator;
import cn.yastarter.generator.core.generator.codeGenerate.resource.PomGenerator;
import cn.yastarter.generator.core.generator.codeGenerate.resource.PropertiesGenerator;
import cn.yastarter.generator.core.generator.dbGenerator.DbGenerator;
import cn.yastarter.generator.core.generator.dbGenerator.DbGeneratorFactory;
import cn.yastarter.generator.core.generator.folderGenerator.FolderGenerator;
import cn.yastarter.generator.core.generator.reader.DbReader;
import cn.yastarter.generator.core.generator.reader.DbReaderFactory;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
//      load config file
        GeneratorConfig.init();
//      init velocity template config
        VelocityConfig.init();
//      create project folder
        FolderGenerator.init();
//        Get the corresponding database instance
        DbReader    dbReader    = DbReaderFactory.createDbReader();
//        get TableBean information
        List<Table> tableList   = dbReader.getTableBeans();
//        get mysql generator
        DbGenerator dbGenerator = DbGeneratorFactory.createDbGenerator();
        for (Table table : tableList) {
//            generate each table
            dbGenerator.generate(table);
        }
        // generate mybatis config file
        dbGenerator.generateConfig(tableList);
        ApplicationGenerator.generateApplication(GeneratorConfig.getBasePackage(),GeneratorConfig.getProjectName());
        try {
//            logback config file generator
            LogConfigGenerator.generateRes();
//            pom file generator
            PomGenerator.generate();
//           application properties generator
            PropertiesGenerator.generateRes();
        } catch (IOException e) {
            log.error("properties file generate error", e);
        }
    }
}
