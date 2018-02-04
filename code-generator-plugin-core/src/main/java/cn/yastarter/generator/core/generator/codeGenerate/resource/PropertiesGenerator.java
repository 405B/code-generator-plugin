package cn.yastarter.generator.core.generator.codeGenerate.resource;

import cn.yastarter.generator.core.common.Constant;
import cn.yastarter.generator.core.config.GeneratorConfig;
import cn.yastarter.generator.core.generator.Generator;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by OovEver on 2018/2/4.
 */
@Slf4j
public class PropertiesGenerator  extends Generator {
    public static void generateRes() throws IOException {
        if (GeneratorConfig.isGenerateResource()) {
            log.info("generate properties ...");
            String outputDir = GeneratorConfig.getOutputDir().concat(Constant.SOURCE_RESOURCE);

            String resourcePath = Velocity.getProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH) + Constant.SIGN_SLASH + GeneratorConfig.getGenerateTemplateLocation() + "/resource";


            VelocityContext context = new VelocityContext();
            context.put("dbUrl", GeneratorConfig.getDbUrl());
            context.put("dbUserName", GeneratorConfig.getDbUserName());
            context.put("dbPassword", GeneratorConfig.getDbPassword());
            context.put("basePackage", GeneratorConfig.getBasePackage());
            context.put("projectName", GeneratorConfig.getProjectName());
            write2FileBySchema("/resource/applicationYmlTemplate.vm", context, outputDir + "application.yml");
        }
    }
}