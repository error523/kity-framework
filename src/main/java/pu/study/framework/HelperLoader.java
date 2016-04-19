package pu.study.framework;

import pu.study.framework.helper.BeanHelper;
import pu.study.framework.helper.ClassHelper;
import pu.study.framework.helper.ControllrtHelper;
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
                ControllrtHelper.class
        };
        for(Class<?> clazz:classes){
            ClassLoaderUtil.loadClass(clazz.getName());
        }
    }
}
