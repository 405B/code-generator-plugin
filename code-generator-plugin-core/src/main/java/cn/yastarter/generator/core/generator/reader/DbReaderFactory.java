package cn.yastarter.generator.core.generator.reader;

import cn.yastarter.generator.core.config.GeneratorConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * @author OovEver
 * 2018/1/18 9:37
 * Db factory to create db instance
 */
@Slf4j
public class DbReaderFactory {
    /**
     * Create an instance for the corresponding database
     * @return the instance for database
     */
    public static DbReader createDbReader() {
        DbReader dbReader;
        switch (GeneratorConfig.getDbType()) {
            case MYSQL:
                // if database is MYSQL
                dbReader = new MysqlDbReader();
                break;
//                if database is ORACLE
            case ORACLE:
                dbReader = null;
                break;
//                other database case:xxx
            default:
                log.error("The database does not exist, please check your config file");
                dbReader = null;
                break;

        }
        return dbReader;
    }
}
