package cn.yastarter.generator.core.generator.codeGenerate.service;

import cn.yastarter.generator.core.bean.Layer;
import cn.yastarter.generator.core.bean.Table;
import cn.yastarter.generator.core.config.GeneratorConfig;
import cn.yastarter.generator.core.generator.Generator;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;

/**
 * generate service code
 * @author OovEver
 * 2018/2/3 18:29
 *
 */
@Slf4j
public class MysqlServiceGenerator extends Generator {
    public static void generate(Table table, String basePackage, String systemPackage, String outputDir) {
        Layer  layer          = GeneratorConfig.getLayer();
        String tableNameClass = table.getTableNameClass();
        log.info("generate biz : {}{}", tableNameClass, layer.getFileSuffixService());
        VelocityContext context = new VelocityContext();
        context.put("basePackage", basePackage);
        context.put("systemPackage", systemPackage);
        context.put("tableNameClass", tableNameClass);
        context.put("tableNameVariable", table.getTableNameVariable());
        context.put("primaryKey", table.getPrimaryKey());
        context.put("layer", layer);
        String outputFilePath = generateOutputFilePath(tableNameClass, outputDir, layer.getLayerNameService(), layer.getFileSuffixService());
        write2FileBySchema("/service/MysqlServiceTemplate.vm", context, outputFilePath);
    }
}
