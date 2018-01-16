package cn.yastarter.generator.core.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.rmi.runtime.Log;

import static org.junit.Assert.*;

/**
 * @author OovEver
 * 2018/1/14 23:10
 */
@Slf4j
public class GeneratorConfigTest {

    @Test
    public void init() {
        log.error("ddd");
        GeneratorConfig.init();
    }
}