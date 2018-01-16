package com.mitewater.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * properties文件读取工具类
 *
 * Created by phq on 2016/4/12.
 * version 1.0.0
 *
 */
public final class PropsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载properties文件
     * @param fileName
     * @return
     */
    public static Properties loadProps(String fileName){
        Properties props = null;
        InputStream ins = null;
        ins = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        try {
            if(ins==null){
                throw new FileNotFoundException(fileName+" file is not found ");
            }
            props = new Properties();
            props.load(ins);
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(ins!=null){
                try{
                    ins.close();
                }catch(IOException e){
                    LOGGER.error("load properties file failure",e);
                }
            }
        }
        return props;
    }

    /**
     * 获取制定属性值，可指定默认值
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString (Properties props, String key, String defaultValue){
        String value = defaultValue;
        if(props.containsKey(key)){
            value = props.getProperty(key);
        }
        return value;
    }

    /**
     *  获取制定属性值，默认为空
     * @param props
     * @param key
     * @return
     */
    public static String getString (Properties props, String key){
        return getString(props,key,"");
    }

    /**
     * 获取int型数值，给定默认值
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static  int getInt (Properties props,String key,int defaultValue){
        int value = defaultValue;
        if(props.containsKey(key)){
            value = CastUtil.castInt(props.getProperty( key));
        }
        return value;
    }

    /**
     *获取INT型值，默认值为0
     * @param props
     * @param key
     * @return
     */
    public static int getInt(Properties props,String key){
        return getInt(props,key,0);
    }

    /**
     * 获取值为long型的数据，默认值自定
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static long getLong(Properties props,String key,long defaultValue){
        long value = defaultValue;
        if(props.containsKey(key)){
            value = CastUtil.castLong(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取long型数据，默认值0
     * @param props
     * @param key
     * @return
     */
    public static  long getLong(Properties props,String key){
        return getLong(props,key,0);
    }

    /**
     * 获取boolean类型的数据，给定默认值
     * @param props
     * @param key
     * @param defaultValue
     * @return
     */
    public static  boolean getBoolean (Properties props,String key,boolean defaultValue){
        boolean value = defaultValue;
        if(props.containsKey(key)){
            value = CastUtil.castBoolean(props.getProperty( key));
        }
        return value;
    }

    /**
     * 获取boolean类型数据
     * @param props
     * @param key
     * @return
     */
    public static boolean getBoolean(Properties props,String key){
        return getBoolean(props,key);
    }

}
