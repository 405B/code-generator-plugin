package cn.yastarter.generator.core;

import cn.yastarter.generator.core.bean.Table;
import cn.yastarter.generator.core.config.GeneratorConfig;
import cn.yastarter.generator.core.config.VelocityConfig;
import cn.yastarter.generator.core.generator.codeGenerate.resource.LogConfigGenerator;
import cn.yastarter.generator.core.generator.codeGenerate.resource.PomGenerator;
import cn.yastarter.generator.core.generator.dbGenerator.DbGenerator;
import cn.yastarter.generator.core.generator.dbGenerator.DbGeneratorFactory;
import cn.yastarter.generator.core.generator.reader.DbReader;
import cn.yastarter.generator.core.generator.reader.DbReaderFactory;
import javafx.scene.control.Tab;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        GeneratorConfig.init();
        VelocityConfig.init();
        try {
//            based on output dir create new folder
            FileUtils.forceMkdir(new File(GeneratorConfig.getOutputDir()));
            log.info("base folder create completed");
        } catch (IOException e) {
            log.error("can not create new folder", e);
        }
//        Clean up all the contents of the folder
        try {
            FileUtils.cleanDirectory(new File(GeneratorConfig.getOutputDir()));
            log.info("base folder clean completed");
        } catch (IOException e) {
            log.error("can not clean folder", e);
        }
//        Get the corresponding database instance
        DbReader    dbReader    = DbReaderFactory.createDbReader();
        List<Table> tableList   = dbReader.getTableBeans();
//        get mysql generator
        DbGenerator dbGenerator = DbGeneratorFactory.createDbGenerator();
        for (Table table : tableList) {
//            generate each table
            dbGenerator.generate(table);
        }
        // generate config code
        dbGenerator.generateConfig(tableList);

        try {
            LogConfigGenerator.generateRes();
            PomGenerator.generate();
        } catch (IOException e) {
            log.error("log file generate error", e);
        }
    }
}
