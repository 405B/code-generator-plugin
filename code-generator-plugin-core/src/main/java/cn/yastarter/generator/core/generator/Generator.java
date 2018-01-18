package cn.yastarter.generator.core.generator;

import cn.yastarter.generator.core.common.Constant;
import cn.yastarter.generator.core.config.GeneratorConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import java.io.*;

/**
 * @author OovEver
 * 2018/1/18 16:16
 */
@Slf4j
public class Generator {

    /**
     * generate output file path
     * @param className class name
     * @param outputDir output location
     * @param layer layer
     * @param layerClassSuffix layer suffix
     * @return output filePath
     */
    protected static String generateOutputFilePath(String className, String outputDir, String layer, String layerClassSuffix) {
        StringBuilder outputFilePath = new StringBuilder(outputDir);
        outputFilePath.append(layer);
        new File(outputFilePath.toString()).mkdir();
        outputFilePath.append(Constant.SIGN_SLASH);
        outputFilePath.append(className);
        outputFilePath.append(layerClassSuffix);
        return outputFilePath.toString();
    }
    /**
     *
     * @param templateFile templateFile name
     * @param context velocity config
     * @param outputFilepath output file location
     */
    protected static void write2FileBySchema(String templateFile, VelocityContext context, String outputFilepath) {
        Template template = getTemplate(GeneratorConfig.getGenerateTemplateLocation().concat(templateFile));
        Writer writer = getWriter(outputFilepath);
        template.merge(context, writer);
        try {
            writer.close();
        } catch (IOException e) {
            log.error("write data fail, exception : ", e);
        }
    }

    /**
     *
     * @param filePath out put file path
     * @return BufferedWriter
     */
    private static Writer getWriter(String filePath) {
        try {
            return new BufferedWriter(new FileWriter(filePath));
        } catch (IOException e) {
            log.error("generate file fail : {}", e);
            throw new RuntimeException();
        }
    }
    /**
     * get template file
     * @param templateFile template file name
     * @return template
     */
    private static Template getTemplate(String templateFile) {
        Template template = null;
        try {
            template = Velocity.getTemplate(templateFile);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            log.error("can not find template " + templateFile,e);
        } catch (ParseErrorException pee) {
            log.error("syntax error in template " + templateFile + ":" + pee);
        }
        return template;
    }
}
