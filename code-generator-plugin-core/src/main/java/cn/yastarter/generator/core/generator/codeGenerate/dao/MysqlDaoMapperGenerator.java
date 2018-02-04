package cn.yastarter.generator.core.generator.codeGenerate.dao;

import cn.yastarter.generator.core.bean.Layer;
import cn.yastarter.generator.core.bean.Table;
import cn.yastarter.generator.core.common.Constant;
import cn.yastarter.generator.core.config.GeneratorConfig;
import cn.yastarter.generator.core.generator.Generator;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;

/**
 * Created by OovEver on 2018/2/4.
 */
@Slf4j
public class MysqlDaoMapperGenerator extends Generator {
    public static void generate(Table table, String systemPackage, String outputDir) {

        String tableNameClass = table.getTableNameClass();
        log.info("generate mapper: {}.xml", tableNameClass);

        VelocityContext context = new VelocityContext();
        context.put("systemPackage", systemPackage);
        context.put("tableNameClass", tableNameClass);
        context.put("tableNameDb", table.getTableNameDb());
        context.put("primaryKey", table.getPrimaryKey());
        context.put("columnList", table.getColumnList());
        context.put("columnSize", table.getColumnList().size());

        Layer layer = GeneratorConfig.getLayer();
        context.put("layer", layer);

        String outputFilePath = outputDir + Constant.SIGN_SLASH + tableNameClass + ".xml";
        write2FileBySchema("/dao/MysqlMapperXml.vm", context, outputFilePath);
    }
}
