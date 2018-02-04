package cn.yastarter.generator.core.generator.codeGenerate.resource;

import cn.yastarter.generator.core.bean.Layer;
import cn.yastarter.generator.core.bean.MybatisMapper;
import cn.yastarter.generator.core.config.GeneratorConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static cn.yastarter.generator.core.generator.Generator.write2FileBySchema;

/**
 * Created by OovEver on 2018/2/4.
 */
@Slf4j
public class MybatisConfigGenerator {
        public static void generate(List<MybatisMapper> mybatisMapperList, String basePackage, String outputDir) {
            log.info("generate mybatis config");
            VelocityContext context = new VelocityContext();
            context.put("basePackage", basePackage);
            context.put("mybatisMapperList", mybatisMapperList);
            Layer layer = GeneratorConfig.getLayer();
            context.put( "layer", layer );
            write2FileBySchema("/resource/MysqlMybatisConfig.vm", context, outputDir.concat("mybatis.xml"));
        }

}
