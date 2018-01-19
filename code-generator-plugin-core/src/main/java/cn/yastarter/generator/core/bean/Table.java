package cn.yastarter.generator.core.bean;

import cn.yastarter.generator.core.common.Constant;
import cn.yastarter.generator.core.config.GeneratorConfig;
import cn.yastarter.generator.core.util.ConvertUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author OovEver
 * 2018/1/18 9:30
 */
public class Table {
    /**
     * table name of database for example "sys_user"
     */
    private final  String       tableNameDb;
    /**
     * Entity class for example "SysUser"
     */
    private final  String       tableNameClass;
    /**
     * Entity class variable name for example "sysUser"
     */
    private final  String       tableNameVariable;
    /**
     * Module name, table name The first underscore part of the front, for example, the table "sys_user" module name "sys"
     */
    private final  String       modleName;
    /**
     * primaryKey of table
     */
    private        PrimaryKey   primaryKey;
    /**
     * Field list of table
     */
    private final List<Column> columnList;
    /**
     * @RequestMapping for generate controller
     */
    private final List<String> tableRequestMapping;
    public Table(String tableName, String primaryKeyName, Map<String, Column> columnMap) {
        this.tableNameDb = tableName;
        if (primaryKeyName != null) {
            Column pkColumn = columnMap.get(primaryKeyName);
            if (pkColumn != null) {
                this.primaryKey = new PrimaryKey(primaryKeyName, pkColumn.getColumnType());
            }
        }
        this.columnList = new ArrayList<>();
        for (Map.Entry<String, Column> entry : columnMap.entrySet()) {
            this.columnList.add(entry.getValue());
        }
        if (GeneratorConfig.getSeparateModel()) {
            this.modleName = separateModelName(tableName);
        } else {
            this.modleName = "";
        }
        this.tableNameClass = ConvertUtil.convert2CamelCase(tableName);
        this.tableNameVariable = ConvertUtil.convert2VariableName(tableNameClass);
        this.tableRequestMapping = ConvertUtil.convert2RequestMappingForCtl(tableNameVariable);
    }
    /**
     *
     *Module name separated
     * @param tableName table name
     * @return the separated table name
     */
    public  String separateModelName(String tableName) {
        int moduleIndex = tableName.indexOf(Constant.SIGN_UNDERLINE);
        if (moduleIndex != -1) {
            return tableName.substring(0, moduleIndex);
        }
        return "";
    }
    public PrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(PrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getTableNameDb() {
        return tableNameDb;
    }

    public String getTableNameClass() {
        return tableNameClass;
    }

    public String getTableNameVariable() {
        return tableNameVariable;
    }

    public String getModleName() {
        return modleName;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public List<String> getTableRequestMapping() {
        return tableRequestMapping;
    }
}
