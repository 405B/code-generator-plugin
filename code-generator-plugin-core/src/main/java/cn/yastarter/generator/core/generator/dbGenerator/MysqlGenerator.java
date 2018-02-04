package cn.yastarter.generator.core.generator.dbGenerator;

import cn.yastarter.generator.core.bean.MybatisMapper;
import cn.yastarter.generator.core.bean.Table;
import cn.yastarter.generator.core.common.Constant;
import cn.yastarter.generator.core.config.GeneratorConfig;
import cn.yastarter.generator.core.generator.codeGenerate.controller.MysqlControllerGenerator;
import cn.yastarter.generator.core.generator.codeGenerate.dao.MysqlDaoGenerator;
import cn.yastarter.generator.core.generator.codeGenerate.dao.MysqlDaoMapperGenerator;
import cn.yastarter.generator.core.generator.codeGenerate.pojo.MysqlPoJoGenerator;
import cn.yastarter.generator.core.generator.codeGenerate.resource.MybatisConfigGenerator;
import cn.yastarter.generator.core.generator.codeGenerate.service.MysqlServiceGenerator;
import cn.yastarter.generator.core.generator.dbGenerator.DbGenerator;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author OovEver
 * 2018/1/18 9:50
 */
@Slf4j
public class MysqlGenerator implements DbGenerator {
    /**
     * generator code
     * @param table the table to generate the code
     */
    @Override
    public void generate(Table table) {
//        table of mysql database
        String tableNameDb = table.getTableNameDb();
        log.info("generate code file for table : {} ", tableNameDb);
//        get output base package config
        String basePackage = GeneratorConfig.getBasePackage();
//        get model package config
        String modelName = table.getModleName();
//       model output location
        String modelPackatge = "";
//        mybatis output location
        String modelMapperPath = "";
//       need to separate table
        if (modelName.length() > 0) {
            modelPackatge = Constant.SIGN_DOT.concat(modelName);
            modelMapperPath = modelName.concat(Constant.SIGN_SLASH);
        }
//        system out put package
        String systemPackage = basePackage + modelPackatge;
//        out put path
        String systemPackageDir = systemPackage.replace(Constant.SIGN_DOT, Constant.SIGN_SLASH);
//base file
        String javaOutputDir = GeneratorConfig.getOutputDir() + Constant.SOURCE_JAVA + systemPackageDir + Constant.SIGN_SLASH;
        new File(javaOutputDir).mkdirs();
        log.info("generate ouput java dir : {}", javaOutputDir);
//mapper file
        String mapperOutputDir = GeneratorConfig.getOutputDir() + Constant.SOURCE_RESOURCE_MAPPER + modelMapperPath;
        new File(mapperOutputDir).mkdirs();
        log.info("generate ouput mapper dir : {}", mapperOutputDir);
//        generator pojo code
        if (GeneratorConfig.isGeneratePojo()) {
            MysqlPoJoGenerator.generate(table, systemPackage, javaOutputDir);
        }
//        generator controller code
        if (GeneratorConfig.isGenerateController()) {
            MysqlControllerGenerator.generate(table, basePackage, systemPackage, javaOutputDir);
        }
        // generator service code
        if (GeneratorConfig.isGenerateService()) {
            MysqlServiceGenerator.generate(table, basePackage, systemPackage, javaOutputDir);
            // generator dao code
            if (GeneratorConfig.isGenerateDao()) {
                MysqlDaoGenerator.generate(table, basePackage, systemPackage, javaOutputDir);
                MysqlDaoMapperGenerator.generate(table, systemPackage, mapperOutputDir);

            }
        }
    }

    /**
     * generator config file
     * @param tableList all table of project
     */
    @Override
    public void generateConfig(List<Table> tableList) {
        if (GeneratorConfig.isGenerateResource()) {
            List<MybatisMapper> mybatisMapperList = new ArrayList<>();
            for (Table table : tableList) {
                mybatisMapperList.add(new MybatisMapper(table.getTableNameClass(), table.getModleName()));
            }
            // basePackage path
            String basePackage = GeneratorConfig.getBasePackage();
            String mapperOutputDir = GeneratorConfig.getOutputDir().concat(Constant.SOURCE_RESOURCE);
            MybatisConfigGenerator.generate(mybatisMapperList, basePackage, mapperOutputDir);
        }
    }
}
