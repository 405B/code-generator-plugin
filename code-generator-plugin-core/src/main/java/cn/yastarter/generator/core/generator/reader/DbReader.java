package cn.yastarter.generator.core.generator.reader;

import cn.yastarter.generator.core.bean.Column;
import cn.yastarter.generator.core.bean.Table;

import java.util.List;
import java.util.Map;

/**
 * @author OovEver
 * 2018/1/18 9:29
 */
public interface DbReader {
    /**
     * get TableBean information
     * @return TableBean information
     */
    List<Table> getTableBeans();

    /**
     * get all TableNames from database
     * @return all table name of database
     */
    List<String> getTableNames();

    /**
     * Get all the columns in the table
     * @param tableName table name
     * @return all the columns of table
     */
    Map<String, Column> getColumns(String tableName);

    /**
     *Get the table's primaryKey name
     * @param tableName table name
     * @return the primaryKey of table
     */
    String getPrimaryKeyName(String tableName);
}
