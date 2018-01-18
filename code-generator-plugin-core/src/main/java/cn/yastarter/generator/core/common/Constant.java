package cn.yastarter.generator.core.common;

/**
 * @author OovEver
 * 2018/1/14 23:02
 */
public class Constant {
    public static final String NOT = "0";
    public static final String YES = "1";

    public static final String SIGN_DOT = ".";
    public static final String SIGN_SLASH = "/";
    public static final String SIGN_UNDERLINE = "_";
    public static final String JAVA_FILE_SUFFIX = ".java";


    public enum DbType {
        /**
         * mysql database
         */
        MYSQL,
        /**
         * oracle database
         */
        ORACLE
    }
}
