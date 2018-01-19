package cn.yastarter.generator.core.generator.codeGenerate.controller;

import cn.yastarter.generator.core.bean.Layer;
import cn.yastarter.generator.core.bean.Table;
import cn.yastarter.generator.core.config.GeneratorConfig;
import cn.yastarter.generator.core.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;

import javax.xml.transform.Templates;
import java.util.HashMap;
import java.util.Map;

import static cn.yastarter.generator.core.generator.Generator.generateOutputFilePath;
import static cn.yastarter.generator.core.generator.Generator.write2FileBySchema;

/**
 * @author OovEver
 * 2018/1/18 22:00
 */
@Slf4j
public class MysqlControllerGenerator {
    public static void generate(Table table, String basePackage, String systemPackage, String outputDir) {
        Layer layer = GeneratorConfig.getLayer();
        String tableNameClass = table.getTableNameClass();
        log.info("generate controller : {}{}", tableNameClass, layer.getFileSuffixController());


        VelocityContext context = new VelocityContext();
        context.put("basePackage", basePackage);
        context.put("systemPackage", systemPackage);
        context.put("tableNameClass", tableNameClass);
        context.put("tableNameVariable", table.getTableNameVariable());
        context.put("requestMapping", table.getTableRequestMapping());
        context.put("primaryKey", table.getPrimaryKey());
        context.put("layer", layer);
        String outputFilePath = generateOutputFilePath(tableNameClass, outputDir, layer.getLayerNameController(), layer.getFileSuffixController());
        write2FileBySchema("/controller/MysqlControllerTemplate.vm", context, outputFilePath);
    }
}
