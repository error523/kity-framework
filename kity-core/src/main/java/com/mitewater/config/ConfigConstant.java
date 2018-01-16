package com.mitewater.config;

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
    //#连接池初始化时的连接数
    String DBCP_INITIALSIZE = "kity.framework.jdbc.initialSize";
    //#连接池的最大数据库连接数。设为0表示无限制
    String DBCP_MAXTOTAL = "kity.framework.jdbc.maxTotal";
    //#最大空闲数，数据库连接的最大空闲时间。超过空闲时间，数据库连
    //#接将被标记为不可用，然后被释放。设为0表示无限制
    String DBCP_MAXIDLE = "kity.framework.jdbc.maxIdle";
    //#最小空闲数
    String DBCP_MINIDLE = "kity.framework.jdbc.minIdle";
    //#最大建立连接等待时间。如果超过此时间将接到异常。设为-1表示无限制
    String DBCP_MAXWAITMILLIS = "kity.framework.jdbc.maxWaitMillis";
    // #超过removeAbandonedTimeout时间后，是否进 行没用连接（废弃）的回收（默认为false，调整为true)
    String DBCP_REMOVEABANDONED = "kity.framework.jdbc.removeAbandoned";
    // #超过时间限制，回收没有用(废弃)的连接（默认为 300秒，调整为180）
    String DBCP_REMOVEABANDONEDTIMEOUT = "kity.framework.jdbc.removeAbandonedTimeout";
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
