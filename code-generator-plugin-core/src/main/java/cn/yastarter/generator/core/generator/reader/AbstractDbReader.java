package cn.yastarter.generator.core.generator.reader;

import cn.yastarter.generator.core.bean.Table;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author OovEver
 * 2018/1/18 10:01
 */
@Slf4j
public abstract class AbstractDbReader implements DbReader {

    public List<Table> getTableBeans() {
        List<Table> tableList = new ArrayList<>();
        List<String> tableNames = getTableNames();
        return new ArrayList<>();
    }


}
