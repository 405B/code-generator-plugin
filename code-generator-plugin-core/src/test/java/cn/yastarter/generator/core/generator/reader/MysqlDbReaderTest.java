package cn.yastarter.generator.core.generator.reader;

import cn.yastarter.generator.core.bean.Table;
import cn.yastarter.generator.core.config.GeneratorConfig;
import cn.yastarter.generator.core.config.VelocityConfig;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author OovEver
 * 2018/1/18 15:58
 */
public class MysqlDbReaderTest {

    @Test
    public void getTableNames() throws IOException {
        // 初始化配置
        GeneratorConfig.init();
        VelocityConfig.init();

        FileUtils.forceMkdir(new File(GeneratorConfig.getOutputDir()));
        FileUtils.cleanDirectory(new File(GeneratorConfig.getOutputDir()));
        DbReader dbReader = DbReaderFactory.createDbReader();
        List<Table> tables = dbReader.getTableBeans();
        System.out.println(tables);
    }

    @Test
    public void getColumns() {
    }

    @Test
    public void getPrimaryKeyName() {
    }
}