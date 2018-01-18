package cn.yastarter.generator.core.generator.dbGenerator;

import cn.yastarter.generator.core.bean.Table;

import java.util.List;

/**
 * @author OovEver
 * 2018/1/18 9:46
 */
public interface DbGenerator {
    /**
     * generator code
     * @param table the table to generate the code
     */
    void generate(Table table);

    /**
     * generator config file
     * @param tableList all table of project
     */
    void generateConfig(List<Table> tableList);
}
