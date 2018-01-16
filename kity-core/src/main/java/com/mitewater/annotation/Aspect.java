package com.mitewater.annotation;

import java.lang.annotation.*;

/**
 * Created by project on 2016/5/8.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
/**
 * 切面注解
 */
public @interface Aspect {
    /**
     * 用于放注解的值
     * @return
     */
    Class<? extends Annotation> value();
}
