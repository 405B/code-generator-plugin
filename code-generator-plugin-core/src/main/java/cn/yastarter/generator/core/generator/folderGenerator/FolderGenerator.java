package cn.yastarter.generator.core.generator.folderGenerator;

import cn.yastarter.generator.core.common.Constant;
import cn.yastarter.generator.core.config.GeneratorConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author OovEver
 * 2018/2/28 23:39
 * generator project folder
 */
@Slf4j
public class FolderGenerator {
    /**
     * init project folder create
     */
    public static void init() {
        //        Clean up all the contents of the folder
        try {
            FileUtils.cleanDirectory(new File(GeneratorConfig.getOutputDir()));
            log.info("base folder clean completed");
        } catch (IOException e) {
            log.error("can not clean folder", e);
        }
        try {
//            based on output dir create new folder
            FileUtils.forceMkdir(new File(GeneratorConfig.getOutputDir()));
            log.info("base folder create completed");
            FileUtils.forceMkdir(new File(GeneratorConfig.getOutputDir()+ Constant.SIGN_SLASH+ Constant.SOURCE_JAVA_TEST));
            log.info("test folder create completed");
        } catch (IOException e) {
            log.error("can not create new folder", e);
        }
    }
}
