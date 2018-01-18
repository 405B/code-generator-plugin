package cn.yastarter.generator.core.bean;

import cn.yastarter.generator.core.util.ConvertUtil;

/**
 * @author OovEver
 * 2018/1/18 10:08
 */
public class PrimaryKey {
    /**
     * The corresponding java primaryKey name，for example “SysUserId”
     */
    private String pkName;

    /**
     * The corresponding java type name，for example“Integer”
     */
    private String pkType;

    /**
     * Database primaryKey name，for example“sys_user_id”
     */
    private String pkNameDb;

    /**
     * primaryKey variable name，for example“sysUserId”
     */
    private String pkNameVariable;
    public PrimaryKey(String pkNameDb, String pkType) {
        this.pkName = ConvertUtil.convert2CamelCase(pkNameDb);
        this.pkType = pkType;
        this.pkNameDb = pkNameDb;
        this.pkNameVariable = ConvertUtil.convert2VariableName(pkName);
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public String getPkType() {
        return pkType;
    }

    public void setPkType(String pkType) {
        this.pkType = pkType;
    }

    public String getPkNameDb() {
        return pkNameDb;
    }

    public void setPkNameDb(String pkNameDb) {
        this.pkNameDb = pkNameDb;
    }

    public String getPkNameVariable() {
        return pkNameVariable;
    }

    public void setPkNameVariable(String pkNameVariable) {
        this.pkNameVariable = pkNameVariable;
    }
}
