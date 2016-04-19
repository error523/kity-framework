package pu.study.framework.helper;

import pu.study.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by project on 2016/4/19.
 */
public final class BeanHelper {
    private static final Map<Class<?>,Object> BEAN_MAP = new HashMap<Class<?>,Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for(Class<?> clazz:beanClassSet){
            Object obj = ReflectionUtil.newInstance(clazz);
            BEAN_MAP.put(clazz,obj);
        }
    }

    /**
     * 获取beanmap
     * @return
     */
    public static Map<Class<?>,Object> getBeanMap(){
        return BEAN_MAP;
    }

    public static<T> T getBean(Class<?> clazz){
        if(BEAN_MAP.containsKey(clazz)){
            throw new RuntimeException("could not found any class :"+clazz);
        }
        return (T) BEAN_MAP.get(clazz);
    }
}
