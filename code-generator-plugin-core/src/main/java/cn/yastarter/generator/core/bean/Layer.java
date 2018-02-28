package cn.yastarter.generator.core.bean;

import static cn.yastarter.generator.core.common.Constant.JAVA_FILE_SUFFIX;
/**
 * @author OovEver
 * 2018/1/18 16:24
 * code output layer
 */
public class Layer {
//  controller file name Used to generate the controller package
//    for example controller
    private String layerNameController;
//    controller file suffix
//    for example Controller.java
    private String fileSuffixController;
//    controller class fileSuffix
//    for example Controller
    private String classSuffixController;
    //  service file name Used to generate the controller package
    private String layerNameService;
    //    service file suffix
    private String fileSuffixService;
    //    service class fileSuffix
    private String classSuffixService;
    //  dao file name Used to generate the controller package
    private String layerNameDao;
    //    dao file suffix
    private String fileSuffixDao;
    //    dao class fileSuffix
    private String classSuffixDao;
    //  pojo file name Used to generate the controller package
    private String layerNamePojo;
    //    pojo file suffix
    private String fileSuffixPojo;
    //    pojo class fileSuffix
    private String classSuffixPojo;

    /**
     *
     * @param classSuffixController controller file suffix
     * @param classSuffixService service file suffix
     * @param classSuffixDao dao file suffix
     * @param classSuffixPojo pojo file suffix
     */
    public Layer(String classSuffixController, String classSuffixService, String classSuffixDao, String classSuffixPojo) {
        this.classSuffixController = classSuffixController;
        this.layerNameController = classSuffixController.toLowerCase();
        this.fileSuffixController = classSuffixController + JAVA_FILE_SUFFIX;

        this.classSuffixService = classSuffixService;
        this.layerNameService = classSuffixService.toLowerCase();
        this.fileSuffixService = classSuffixService + JAVA_FILE_SUFFIX;

        this.classSuffixDao = classSuffixDao;
        this.layerNameDao = classSuffixDao.toLowerCase();
        this.fileSuffixDao = classSuffixDao + JAVA_FILE_SUFFIX;

        this.classSuffixPojo = classSuffixPojo;
        this.layerNamePojo = classSuffixPojo.toLowerCase();
        this.fileSuffixPojo = classSuffixPojo + JAVA_FILE_SUFFIX;
    }


    public static String getJavaFileSuffix() {
        return JAVA_FILE_SUFFIX;
    }

        public String getLayerNameController() {
        return layerNameController;
    }

    public String getFileSuffixController() {
        return fileSuffixController;
    }

    public String getClassSuffixController() {
        return classSuffixController;
    }

    public String getLayerNameService() {
        return layerNameService;
    }

    public String getFileSuffixService() {
        return fileSuffixService;
    }

    public String getClassSuffixService() {
        return classSuffixService;
    }

    public String getLayerNameDao() {
        return layerNameDao;
    }

    public String getFileSuffixDao() {
        return fileSuffixDao;
    }

    public String getClassSuffixDao() {
        return classSuffixDao;
    }

    public String getLayerNamePojo() {
        return layerNamePojo;
    }

    public String getFileSuffixPojo() {
        return fileSuffixPojo;
    }

    public String getClassSuffixPojo() {
        return classSuffixPojo;
    }
}
