package cn.yastarter.generator.core.generator.codeGenerate;

import cn.yastarter.generator.core.common.Constant;
import cn.yastarter.generator.core.config.GeneratorConfig;
import cn.yastarter.generator.core.util.ConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;

import java.io.IOException;

import static cn.yastarter.generator.core.generator.Generator.generateOutputFilePath;
import static cn.yastarter.generator.core.generator.Generator.write2FileBySchema;

/**
 * @author OovEver
 * 2018/2/27 22:59
 */
@Slf4j
public class ApplicationGenerator {
    public static void generateApplication(String basePackage,String projectName) {
        log.info("generate ProjectApplication ...");
        projectName = ConvertUtil.toUpperCase(projectName.charAt(0)) + projectName.substring(1);
        VelocityContext context = new VelocityContext();
        context.put("basePackage", basePackage);
        context.put("projectName", projectName);
        String className = projectName+ "Application"+".java";
        String outputDir = GeneratorConfig.getOutputDir() + Constant.SOURCE_JAVA +Constant.SIGN_SLASH+basePackage.replace(Constant.SIGN_DOT, Constant.SIGN_SLASH)+Constant.SIGN_SLASH;
        write2FileBySchema("/projectApplication.vm", context, outputDir.concat(className));
    }
}
