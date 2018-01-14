package cn.yastarter.generator.core.config;

import cn.yastarter.generator.core.common.Constant;
import cn.yastarter.generator.core.common.Constant.DbType;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
/**
 * @author OovEver
 * 2018/1/14 19:52
 * config file settings
 */
@Slf4j
public class GeneratorConfig {
    private static final String CONFIG_FILE_PATH = "config";

//database config
    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_TYPE = "db.type";
    private static final String DB_DRIVER = "db.driver";
//project properties
    private static final String PROJECT_NAME = "project.name";
    private static final String BASE_PACKAGE = "base.package";
    private static final String SEPARATE_MODLE = "separate.modle";
    private static final String TABLE_PREFIX = "table.prefix";


    private static final String GEN_POJO = "gen.pojo";
    private static final String GEN_CONTROLLER = "gen.controller";
    private static final String GEN_SERVICE = "gen.service";
    private static final String GEN_DAO = "gen.dao";
    private static final String GEN_TOOL = "gen.tool";
    private static final String GEN_RESOURCE = "gen.resource";


    private static final String CLASS_SUFFIX_CONTROLLER = "class.suffix.controller";
    private static final String CLASS_SUFFIX_SERVICE = "class.suffix.service";
    private static final String CLASS_SUFFIX_DAO = "class.suffix.dao";
    private static final String CLASS_SUFFIX_POJO = "class.suffix.pojo";

// Generate code location
    private static final String OUTPUT_DIR = "output.dir";

    //template
    private static final String GENERATE_TEMPLATE = "generate.template";
    private static final String GENERATE_TEMPLATE_LOCATION = "generate.template.location";

    private static final HashMap<String, String> CONFIGS = new HashMap<>();

    /**
     * Initialize the config information
     * load the config file
     */
    public static void init() {
        ResourceBundle resourceBundle = PropertyResourceBundle.getBundle(CONFIG_FILE_PATH);
        for (String key : resourceBundle.keySet()) {
            CONFIGS.put(key, resourceBundle.getString(key));
        }
        log.info("the config file has loaded");
    }
    /**
     * set property
     * @param key key value
     * @param value The value need to set
     */
    public static void set(String key, String value) {
        CONFIGS.put(key, value);
    }
    public static DbType getDbType() {
        String dbTypeStr = CONFIGS.get(DB_TYPE);
        return DbType.valueOf(dbTypeStr);
    }
    public static String getDbDriver() {
        return CONFIGS.get(DB_DRIVER);
    }
    public static String getDbUrl() {
        return CONFIGS.get(DB_URL);
    }
    public static String getDbUserName() {
        return CONFIGS.get(DB_USERNAME);
    }
    public static String getDbPassword() {
        return CONFIGS.get(DB_PASSWORD);
    }
    public static String getTablePrefix() {
        return CONFIGS.get(TABLE_PREFIX);
    }
    public static String getProjectName() {
        return CONFIGS.get(PROJECT_NAME);
    }
    public static String getBasePackage() {
        return CONFIGS.get(BASE_PACKAGE);
    }
    public static boolean getSeparateModle() {
        return CONFIGS.get(SEPARATE_MODLE).endsWith(Constant.YES);
    }
    public static String getOutputDir() {
        return CONFIGS.get(OUTPUT_DIR);
    }
    public static String getGenerateTemplate() {
        return CONFIGS.get(GENERATE_TEMPLATE);
    }
    public static String getGenerateTemplateLocation() {
        return CONFIGS.get(GENERATE_TEMPLATE_LOCATION);
    }
    public static boolean isGenerateController() {
        return CONFIGS.get(GEN_CONTROLLER).endsWith(Constant.YES);
    }

    public static boolean isGenerateService() {
        return CONFIGS.get(GEN_SERVICE).endsWith(Constant.YES);
    }

    public static boolean isGenerateDao() {
        return CONFIGS.get(GEN_DAO).endsWith(Constant.YES);
    }

    public static boolean isGeneratePojo() {
        return CONFIGS.get(GEN_POJO).endsWith(Constant.YES);
    }
    public static boolean isGenerateTool() {
        return CONFIGS.get(GEN_TOOL).endsWith(Constant.YES);
    }

    public static boolean isGenerateResource() {
        return CONFIGS.get(GEN_RESOURCE).endsWith(Constant.YES);
    }
    public static String getClassSuffixController() {
        return CONFIGS.get(CLASS_SUFFIX_CONTROLLER);
    }

    public static String getClassSuffixService() {
        return CONFIGS.get(CLASS_SUFFIX_SERVICE);
    }

    public static String getClassSuffixDao() {
        return CONFIGS.get(CLASS_SUFFIX_DAO);
    }

    public static String getClassSuffixPojo() {
        return CONFIGS.get(CLASS_SUFFIX_POJO);
    }
}
