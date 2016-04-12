package pu.study.framework;

/**
 * Created by phq on 2016/4/12.
 * version 1.0.0
 */
public interface ConfigConstant {
    /**
     *配置文件名称
     */
    String CONFIG_FILE = "platform.properties";
    /**
     *JDBC的一些配置
     */
    String JDBC_DRIVER = "kity.framework.jdbc.driver";
    String JDBC_URL = "kity.framework.jdbc.url";
    String JDBC_USERNAME = "kity.framework.jdbc.username";
    String JDBC_PASSWORD = "kity.framework.jdbc.password";
    /**
     * 系统基本路径的一些配置
     */
    //基本包路径
    String BASE_PACKAGE = "kity.framework.app.base_package";
    //前端JSP页面存放路径
    String JSP_PATH = "kity.framework.app.jsp_path";
    //前端静态文件存放路径
    String ASSRT_PATH = "kity.framework.app.asset_path";
}
