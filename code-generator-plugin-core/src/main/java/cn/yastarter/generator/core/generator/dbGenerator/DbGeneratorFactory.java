package cn.yastarter.generator.core.generator.dbGenerator;

import cn.yastarter.generator.core.config.GeneratorConfig;



/**
 * @author OovEver
 * 2018/1/18 16:13
 * dbGenerator factory
 */
public class DbGeneratorFactory {
    DbGenerator dbGenerator;
    public static DbGenerator createDbGenerator() {
        DbGenerator dbGenerator;
        switch (GeneratorConfig.getDbType()) {
            case MYSQL:
                // MYSQL
                dbGenerator = new MysqlGenerator();
                break;
            case ORACLE:
                // ORACLE
                dbGenerator = null;
                break;
//                other
            default:
                dbGenerator = null;
                break;
        }
        return dbGenerator;
    }
}
