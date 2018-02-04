package cn.yastarter.generator.core.generator.codeGenerate.resource;

import cn.yastarter.generator.core.config.GeneratorConfig;
import cn.yastarter.generator.core.generator.Generator;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;

/**
 * Created by OovEver on 2018/2/4.
 */
@Slf4j
public class PomGenerator extends Generator {
    public static void generate() {
        log.info("generate pom ...");
        VelocityContext context = new VelocityContext();
        context.put("projectName", GeneratorConfig.getProjectName());
        context.put("basePackage", GeneratorConfig.getBasePackage());
        write2FileBySchema("/pom.vm", context, GeneratorConfig.getOutputDir().concat("pom.xml"));
    }
}
