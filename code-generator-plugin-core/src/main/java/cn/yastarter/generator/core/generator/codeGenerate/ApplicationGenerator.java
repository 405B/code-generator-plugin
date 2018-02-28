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
    /**
     * generate spring boot startup file
     * @param basePackage project basePackage
     * @param projectName projectName for code
     */
    public static void generateApplication(String basePackage,String projectName) {
        log.info("generate ProjectApplication ...");
        projectName = ConvertUtil.convertFirstCharUpper(projectName);
        VelocityContext context = new VelocityContext();
        context.put("basePackage", basePackage);
        context.put("projectName", projectName);
        String className = projectName+ "Application"+".java";
        String outputDir = GeneratorConfig.getOutputDir() + Constant.SOURCE_JAVA +Constant.SIGN_SLASH+basePackage.replace(Constant.SIGN_DOT, Constant.SIGN_SLASH)+Constant.SIGN_SLASH;
        write2FileBySchema("/projectApplication.vm", context, outputDir.concat(className));
        log.info("finish generate ProjectApplication");
    }
}
