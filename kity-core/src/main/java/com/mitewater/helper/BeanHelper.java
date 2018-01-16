package com.mitewater.helper;

import com.mitewater.utils.JSONUtil;
import com.mitewater.utils.ReflectionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by project on 2016/4/19.
 */
public final class BeanHelper {
    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanHelper.class);
    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for(Class<?> clazz:beanClassSet){
            Object obj = ReflectionUtil.newInstance(clazz);
            BEAN_MAP.put(clazz,obj);
        }
        LOGGER.debug("初始化类MAP："+ JSONUtil.toJson(BEAN_MAP));
    }

    /**
     * 获取beanmap
     * @return
     */
    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    public static<T> T getBean(Class<?> clazz){
        if(!BEAN_MAP.containsKey(clazz)){
            throw new RuntimeException("could not found any class :"+clazz);
        }
        return (T) BEAN_MAP.get(clazz);
    }
}
