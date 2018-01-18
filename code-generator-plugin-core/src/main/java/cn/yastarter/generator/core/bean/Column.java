package cn.yastarter.generator.core.bean;

import cn.yastarter.generator.core.util.ConvertUtil;

/**
 * @author OovEver
 * 2018/1/18 9:31
 * Database table fields
 */
public class Column {
    /**
     * The corresponding java field name, such as "LoginName"
     */
    private final String columnName;

    /**
     * The corresponding java type name, such as "Integer"
     */
    private final String columnType;

    /**
     * Description information
     */
    private final String columneRemarks;

    /**
     * Database field name, for example "login_name"
     */
    private final String columnNameDb;

    /**
     * Field variable name, such as "loginName"
     */
    private final String columnNameVariable;
    public Column(String columnNameDb, int dbDateType, String columneRemarks) {
        this.columnName = ConvertUtil.convert2CamelCase(columnNameDb);
        this.columnType = ConvertUtil.convert2JavaType(dbDateType);
        this.columnNameDb = columnNameDb;
        this.columneRemarks = columneRemarks;
        this.columnNameVariable = ConvertUtil.convert2VariableName(columnName);
    }
    public String getColumnName() {
        return columnName;
    }

    public String getColumnType() {
        return columnType;
    }

    public String getColumneRemarks() {
        return columneRemarks;
    }

    public String getColumnNameDb() {
        return columnNameDb;
    }

    public String getColumnNameVariable() {
        return columnNameVariable;
    }
}
