package cn.yastarter.generator.core.generator.reader;

import cn.yastarter.generator.core.bean.Column;
import cn.yastarter.generator.core.config.GeneratorConfig;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author OovEver
 * 2018/1/18 11:00
 * Get mysql data information
 */
@Slf4j
public class MysqlDbReader extends AbstractDbReader{
    /**
     * get all TableNames from database
     * @return all table name of database
     */
    @Override
    public List<String> getTableNames() {
        List<String> tableList = new ArrayList<>();
        String tablePrefix = GeneratorConfig.getTablePrefix();
        Connection conn = getConn();
        ResultSet tableNamesRS = null;
        String[] types = {"TABLE", "VIEW"};
        try {
//Get the result set (rs) structure, such as the number of fields, field names.
            DatabaseMetaData dbmd = conn.getMetaData();
            tableNamesRS = dbmd.getTables(null, null, null, types);
            while (tableNamesRS.next()) {
                String tableName = tableNamesRS.getString("TABLE_NAME");
                if (tableName.startsWith(tablePrefix)) {
                    tableList.add(tableName);
                    log.info("get table : {}", tableName);
                }
            }
        } catch (SQLException e) {
            log.error("your log info, exception : ", e);
        } finally {
            closeRS(tableNamesRS);
            closeConn(conn);
        }
        return tableList;
    }
    /**
     * Get all the columns in the table
     * @param tableName table name
     * @return all the columns of table
     */
    @Override
    public Map<String, Column> getColumns(String tableName) {
        Map<String, Column> cloumList = new LinkedHashMap<String, Column>();
        Connection conn = getConn();
        ResultSet columnNamesRS = null;
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
//            get column
            columnNamesRS = dbmd.getColumns(null, null, tableName, null);
            while (columnNamesRS.next()) {
//                remark of column
                String columnRemarks = columnNamesRS.getString("REMARKS");
//                column name
                String columnName = columnNamesRS.getString("COLUMN_NAME");
//                field dataType
                int dateType = columnNamesRS.getInt("DATA_TYPE");
                Column column = new Column(columnName, dateType, columnRemarks);
                cloumList.put(columnName, column);
                log.info("get table column, table : {}, column : {}", tableName, columnName);
            }
        } catch (SQLException e) {
            log.error("your log info, exception : ", e);
        }finally {
            closeRS(columnNamesRS);
            closeConn(conn);
        }
        return cloumList;
    }
    /**
     *Get the table's primaryKey name
     * @param tableName table name
     * @return the primaryKey of table
     */
    @Override
    public String getPrimaryKeyName(String tableName) {
        Connection conn = getConn();
        ResultSet primaryKeyRS = null;
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
//            get primary key
            primaryKeyRS = dbmd.getPrimaryKeys(null, null, tableName);
            while (primaryKeyRS.next()) {
                String primaryKeyName = primaryKeyRS.getString("COLUMN_NAME");
                log.info("get table primaryKey, table : {}, primaryKey : {}", tableName, primaryKeyName);
                return primaryKeyName;
            }
        } catch (SQLException e) {
            log.error("your log info, exception : ", e);
        } finally {
            closeRS(primaryKeyRS);
            closeConn(conn);
        }
        return null;
    }
}
