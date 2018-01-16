package com.mitewater.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by project on 2016/4/19.
 */
public final class ReflectionUtil {
    private static final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * 实例化类
     * @param clazz
     * @return
     */
    public static Object newInstance(Class<?> clazz){
        Object instance = null;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            logger.error("newInstance failure",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 调用方法
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokMethod(Object obj, Method method,Object...args){
        Object result;
        try{
            method.setAccessible(true);
            result = method.invoke(obj,args);
        }catch(Exception e){
            logger.error("invoke failure ", e);
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 设置成员变量
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj, Field field, Object value){
        try{
            field.setAccessible(true);
            field.set(obj,value);
        }catch(Exception e){
            logger.error("set field failure",e);
            throw new RuntimeException(e);
        }
    }
}
