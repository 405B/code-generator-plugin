package cn.yastarter.generator.core.generator.dbGenerator;

import cn.yastarter.generator.core.bean.Table;
import cn.yastarter.generator.core.generator.dbGenerator.DbGenerator;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author OovEver
 * 2018/1/18 9:50
 */
@Slf4j
public class MysqlGenerator implements DbGenerator {
    /**
     * generator code
     * @param table the table to generate the code
     */
    @Override
    public void generate(Table table) {

    }

    /**
     * generator config file
     * @param tableList all table of project
     */
    @Override
    public void generateConfig(List<Table> tableList) {

    }
}
