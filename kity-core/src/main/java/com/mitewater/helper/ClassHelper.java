package com.mitewater.helper;



import com.mitewater.annotation.Controller;
import com.mitewater.annotation.Service;
import com.mitewater.utils.ClassLoaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by project on 2016/4/19.
 */
public final class ClassHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassHelper.class);
    private static final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        LOGGER.debug("获得基础包路径成功："+basePackage);
        CLASS_SET = ClassLoaderUtil.getClassSet(basePackage);
    }

    public Set<Class<?>> getClassSet(){
        return CLASS_SET;
    }

    /**
     * 获取所有service注解的类
     * @return
     */
    public static Set<Class<?>> getServiceClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> clazz:CLASS_SET){
            if(clazz.isAnnotationPresent(Service.class)){
                classSet.add(clazz);
            }
        }
        return classSet;
    }

    /**
     * 获取所有controller注解的类
     * @return
     */
    public static Set<Class<?>> getControllerClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> clazz:CLASS_SET){
            if(clazz.isAnnotationPresent(Controller.class)){
                classSet.add(clazz);
            }
        }
        return classSet;
    }

    /**
     * 获取所有Bean注解的类
     * @return
     */
    public static Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        classSet.addAll(getServiceClassSet());
        classSet.addAll(getControllerClassSet());
        return classSet;
    }
}
