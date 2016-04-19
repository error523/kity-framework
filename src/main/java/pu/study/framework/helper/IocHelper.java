package pu.study.framework.helper;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import pu.study.framework.annotation.Inject;
import pu.study.framework.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by project on 2016/4/19.
 */
public final class IocHelper {
    static {
        //获取所有的bean
        Map<Class<?>,Object> beanMap = BeanHelper.getBeanMap();
        if(MapUtils.isNotEmpty(beanMap)){
            for(Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet()){
                Class<?> beanClass = beanEntry.getKey();
                Object beanObject = beanEntry.getValue();
                //获取bean类中的所有成员变量
                Field[] beanFields = beanClass.getFields();
                if(ArrayUtils.isNotEmpty(beanFields)){
                    for(Field beanfield:beanFields){
                        //判断该成员变量是否带有Inject注解，如果带了那么下面就要该是注入实例了
                        if(beanfield.isAnnotationPresent(Inject.class)){
                            //获取改成员变量的class类型
                            Class<?> beanFieldClass = beanfield.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass.getClass());
                            //不为空，通过反射将实例赋给该类的成员变量
                            if(beanFieldInstance!=null){
                                ReflectionUtil.setField(beanObject,beanfield,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
