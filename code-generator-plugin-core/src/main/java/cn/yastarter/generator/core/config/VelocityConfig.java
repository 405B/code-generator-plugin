package cn.yastarter.generator.core.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import sun.rmi.runtime.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * @author OovEver
 * 2018/1/16 22:45
 * Velocity template Config
 */
@Slf4j
public class VelocityConfig {
    /**
     * init velocity template config
     */
    public static void init() {
        Velocity.init(generateProperties());
    }

    /**
     * Get property
     * @return property
     */
    private static Properties generateProperties() {
        Properties properties = new Properties();
        properties.setProperty(VelocityEngine.INPUT_ENCODING, "UTF-8");
        properties.setProperty(VelocityEngine.OUTPUT_ENCODING, "UTF-8");
        try {
            String path = URLDecoder.decode(VelocityConfig.class.getResource("/").getPath(),"UTF8");
            properties.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, path.concat("template"));
            log.info("template init success");
        } catch (UnsupportedEncodingException e) {
            log.error("the velocity template init error", e);
        }

        return properties;
    }
}
