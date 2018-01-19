package cn.yastarter.generator.core.generator.codeGenerate.pojo;

import cn.yastarter.generator.core.bean.Layer;
import cn.yastarter.generator.core.bean.Table;
import cn.yastarter.generator.core.config.GeneratorConfig;
import cn.yastarter.generator.core.generator.Generator;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;

/**
 * @author OovEver
 * 2018/1/18 17:25
 */
@Slf4j
public class MysqlPoJoGenerator extends Generator {
    public static void generate(Table table, String systemPackage, String outputDir) {
//        get layer for pojo output
        Layer layer = GeneratorConfig.getLayer();
        String tableNameClass = table.getTableNameClass();
        log.info("generate : {}{}", tableNameClass, layer.getFileSuffixPojo());
        VelocityContext context = new VelocityContext();
        context.put("systemPackage", systemPackage);
        context.put("tableNameClass", tableNameClass);
        context.put("columnList", table.getColumnList());
        context.put("layer", layer);
        String outputFilePath = generateOutputFilePath(tableNameClass, outputDir, layer.getLayerNamePojo(), layer.getFileSuffixPojo());
        write2FileBySchema("/pojo/MysqlPoTemplate.vm", context, outputFilePath);
    }
}
