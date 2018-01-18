package cn.yastarter.generator.core.util;

import cn.yastarter.generator.core.common.Constant;

import java.sql.Types;

/**
 * @author OovEver
 * 2018/1/18 10:12
 * Character conversion tools
 */
public class ConvertUtil {
    /**
     *  Convert database name to camel name
     *  for example "sys_user" to "SysUser"
     * @param nameInDb the name int database
     * @return camelName
     */
    public static String convert2CamelCase(String nameInDb) {
        StringBuilder camelCaseName = new StringBuilder();
        String[] camelCaseNamePartArr = nameInDb.split(Constant.SIGN_UNDERLINE);
        for (String enumNamePart : camelCaseNamePartArr) {
            if (enumNamePart.length() == 0) {
                continue;
            }
            char firstChar = enumNamePart.charAt(0);
            firstChar = toUpperCase(firstChar);
            camelCaseName.append(firstChar);
            if (enumNamePart.length() > 1) {
                camelCaseName.append(enumNamePart.substring(1));
            }
        }
        return camelCaseName.toString();
    }
    /**
     * <B>Convert database type to java type</B><br>
     *
     * @param dbDateType database type
     * @return java type
     */
    public static String convert2JavaType(int dbDateType) {
        String javaType;
        switch (dbDateType) {
            case Types.TINYINT:
                javaType = "short";
                break;
            case Types.SMALLINT:
                javaType = "short";
                break;
            case Types.INTEGER:
                javaType = "int";
                break;
            case Types.BIGINT:
                javaType = "long";
                break;
            case Types.FLOAT:
                javaType = "float";
                break;
            case Types.DOUBLE:
                javaType = "double";
                break;
            case Types.CHAR:
                javaType = "String";
                break;
            case Types.VARCHAR:
                javaType = "String";
                break;
            case Types.TIME:
                javaType = "Date";
                break;
            case Types.DATE:
                javaType = "Date";
                break;
            case Types.TIMESTAMP:
                javaType = "Date";
                break;
            case Types.BOOLEAN:
                javaType = "boolean";
                break;
            default:
                javaType = "String";
                break;
        }
        return javaType;
    }

    /**
     * Convert the camelCaseName to a variable name
     * @param camelCaseName the camelName
     * @return basic variable name
     */
    public static String convert2VariableName(String camelCaseName) {
        char firstChar = camelCaseName.charAt(0);
        firstChar = toLowerCase(firstChar);
        StringBuilder result = new StringBuilder();
        result.append(firstChar);
        if (camelCaseName.length() > 1) {
            result.append(camelCaseName.substring(1));
        }
        return result.toString();
    }
    /**
     * convert a char to upperCase
     * @param c The character to be converted
     * @return upperCase char
     */
    private static char toUpperCase(char c) {
        if (c >= 'a' && c <= 'z') {
            c = (char) (c - 32);
        }
        return c;
    }

    /**
     * convert a char to lowerCase
     * @param c The character to be converted
     * @return lowerCase char
     */
    private static char toLowerCase(char c) {
        if (c >= 'A' && c <= 'Z') {
            c = (char) (c + 32);
        }
        return c;
    }
}
