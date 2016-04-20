package pu.study.framework.core;

import pu.study.framework.helper.BeanHelper;
import pu.study.framework.helper.ClassHelper;
import pu.study.framework.helper.ControllerHelper;
import pu.study.framework.helper.IocHelper;
import pu.study.framework.util.ClassLoaderUtil;

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
