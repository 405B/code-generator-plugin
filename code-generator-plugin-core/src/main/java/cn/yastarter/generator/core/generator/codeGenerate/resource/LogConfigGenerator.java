package cn.yastarter.generator.core.generator.codeGenerate.resource;

import cn.yastarter.generator.core.common.Constant;
import cn.yastarter.generator.core.config.GeneratorConfig;
import cn.yastarter.generator.core.generator.Generator;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by OovEver on 2018/2/4.
 */
@Slf4j
public class LogConfigGenerator extends Generator {
    /**
     * generate logback config file
     * @throws IOException file not found
     */
    public static void generateRes() throws IOException {
        if (GeneratorConfig.isGenerateResource()) {
            log.info("generate log config");

            String resourcePath = Velocity.getProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH) + Constant.SIGN_SLASH + GeneratorConfig.getGenerateTemplateLocation() + "/resource";
            File file = new File(resourcePath);
            File[] logConfigFiles = file.listFiles((dir, name) -> name.startsWith("log"));
            if (logConfigFiles == null) {
                return;
            }
            for (File logConfigFile : logConfigFiles) {
                Files.copy(Paths.get(logConfigFile.getPath()), Paths.get(GeneratorConfig.getOutputDir() + Constant.SOURCE_RESOURCE + logConfigFile.getName()));
            }

        }
    }
}
