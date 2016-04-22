package pu.study.framework.helper;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pu.study.framework.util.PropsUtil;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by project on 2016/4/20.
 */
public final class DataBaseHelper {
    private final static Logger LOGGER;
    private static String DRIVER;
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;
    private static final ThreadLocal<Connection> CONNECTION_THREAD_LOCAL;
    private static final QueryRunner QUERY_RUNNER;
    private static final BasicDataSource BASIC_DATA_SOURCE;
    static {
        LOGGER = LoggerFactory.getLogger(DataBaseHelper.class);
        DRIVER = ConfigHelper.getJdbcDriver();
        URL = ConfigHelper.getJdbcUrl();
        USERNAME = ConfigHelper.getJdbcUsername();
        PASSWORD = ConfigHelper.getJdbcPassword();
        CONNECTION_THREAD_LOCAL = new ThreadLocal<Connection>();
        QUERY_RUNNER = new QueryRunner();
        //dbpc2连接池
        BASIC_DATA_SOURCE = new BasicDataSource();
        BASIC_DATA_SOURCE.setDriverClassName(DRIVER);
        BASIC_DATA_SOURCE.setUrl(URL);
        BASIC_DATA_SOURCE.setUsername(USERNAME);
        BASIC_DATA_SOURCE.setPassword(PASSWORD);
        BASIC_DATA_SOURCE.setInitialSize(ConfigHelper.getDbcpInitialSize());
        BASIC_DATA_SOURCE.setMaxTotal(ConfigHelper.getDbcpMaxTotal());
        BASIC_DATA_SOURCE.setMaxIdle(ConfigHelper.getDbcpMaxIdle());
        BASIC_DATA_SOURCE.setMinIdle(ConfigHelper.getDbcpMinIdle());
        BASIC_DATA_SOURCE.setMaxWaitMillis(ConfigHelper.getMaxWaitMillis());
    }

    public static Connection getConnection(){
        Connection connection = CONNECTION_THREAD_LOCAL.get();
        if(null==connection){
            try {
                connection = BASIC_DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("获取数据库连接失败",e);
                throw new RuntimeException(e);
            }finally {
                CONNECTION_THREAD_LOCAL.set(connection);
            }
        }
        return connection;
    }

    public static List<Map<String,Object>> queryMapList(String sql, Object...param){
        List<Map<String,Object>> list = null;
        try {
            LOGGER.debug("---------------开始查询数据---------------");
            Connection conn = getConnection();
            list = QUERY_RUNNER.query(conn,sql,new MapListHandler(),param);
        } catch (SQLException e) {
            LOGGER.error("查询失败",e);
            throw new RuntimeException(e);
        }
        return list;
    }
}
