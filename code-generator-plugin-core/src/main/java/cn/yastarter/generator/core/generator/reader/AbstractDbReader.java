package cn.yastarter.generator.core.generator.reader;

import cn.yastarter.generator.core.bean.Table;
import cn.yastarter.generator.core.config.GeneratorConfig;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author OovEver
 * 2018/1/18 10:01
 */
@Slf4j
public abstract class AbstractDbReader implements DbReader {
    /**
     * get TableBean information
     * @return TableBean information
     */
    public List<Table> getTableBeans() {
        List<Table> tableList = new ArrayList<>();
        List<String> tableNames = getTableNames();
        for (String tableName : tableNames) {
            Table table = new Table(tableName, getPrimaryKeyName(tableName), getColumns(tableName));
            tableList.add(table);
        }
        return tableList;
    }

    /**
     * Get the database connection
     * @return database connection
     */
    Connection getConn() {
        try {
            Class.forName(GeneratorConfig.getDbDriver());
        } catch (ClassNotFoundException e) {
            log.error("find driver fail :　", e);
        }
        String url = GeneratorConfig.getDbUrl();
        String username = GeneratorConfig.getDbUserName();
        String password = GeneratorConfig.getDbPassword();
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            log.error("get connection fail : ", e);
        }
        return null;
    }

    /**
     * close the database connection
     * @param conn the database connection
     */
    void closeConn(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.error("close connect fail : ", e);
            }
        }
    }
    /**
     * close the ResultSet
     * @param rs the ResultSet
     */
    void closeRS(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("close result set fail : ", e);
            }
        }
    }
}
