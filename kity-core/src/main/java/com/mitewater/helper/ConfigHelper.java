package com.mitewater.helper;


import com.mitewater.config.ConfigConstant;
import com.mitewater.utils.PropsUtil;

import java.util.Properties;

/**
 * 配置文件读取和相关操作类
 * Created by phq on 2016/4/12.
 * version 1.0.1
 */
public class ConfigHelper {
    /**
     * 初始化配置文件
     */
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     *获取jdbc驱动
     * @return
     */
    public static String getJdbcDriver(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
    }

    /**
     * 获取JDBC url
     * @return
     */
    public static  String getJdbcUrl(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_URL);
    }

    /**
     * 获取JDBC username
     * @return
     */
    public static  String getJdbcUsername(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_USERNAME);
    }

    /**
     * 获取JDBC password
     * @return
     */
    public static String getJdbcPassword(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_PASSWORD);
    }

    public static int getDbcpMaxTotal(){
        return PropsUtil.getInt(CONFIG_PROPS,ConfigConstant.DBCP_MAXTOTAL);
    }

    public static int getDbcpMaxIdle(){
        return PropsUtil.getInt(CONFIG_PROPS,ConfigConstant.DBCP_MAXIDLE);
    }
    public static int getDbcpMinIdle(){
        return PropsUtil.getInt(CONFIG_PROPS,ConfigConstant.DBCP_MINIDLE);
    }

    public static int getDbcpInitialSize(){
        return PropsUtil.getInt(CONFIG_PROPS,ConfigConstant.DBCP_INITIALSIZE);
    }

    public static long getMaxWaitMillis(){
        return PropsUtil.getLong(CONFIG_PROPS,ConfigConstant.DBCP_MAXWAITMILLIS);
    }

    /**
     * 获取应用JSP路径
     * @return
     */
    public static String getAppJspPath(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JSP_PATH,"/WEB-INF/view/");
    }

    /**
     * 获取应用的基础包
     * @return
     */
    public static  String getAppBasePackage(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.BASE_PACKAGE);
    }

    /**
     * 获取静态资源目录
     * @return
     */
    public static  String getAppBaseAssetPath(){
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.ASSRT_PATH,"/asset/");
    }

}
