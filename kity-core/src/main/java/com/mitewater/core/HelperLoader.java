package com.mitewater.core;


import com.mitewater.helper.BeanHelper;
import com.mitewater.helper.ClassHelper;
import com.mitewater.helper.ControllerHelper;
import com.mitewater.helper.IocHelper;
import com.mitewater.utils.ClassLoaderUtil;

/**
 * Created by project on 2016/4/19.
 */
public final class HelperLoader {
    public static void init(){
        Class<?>[] classes = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for(Class<?> clazz:classes){
            ClassLoaderUtil.loadClass(clazz.getName());
        }
    }
}
