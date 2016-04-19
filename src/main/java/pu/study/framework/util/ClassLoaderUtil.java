package pu.study.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * 一个简单的类加载器
 *
 * Created by phq on 2016/4/12.
 *
 * version 1.0.0
 */
public class ClassLoaderUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassLoaderUtil.class);

    /**
     * 获取当前的ClassLoader
     * @return
     */
    public static ClassLoader getClassLoader(){
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 根据类的名称加载类
     * @param className
     * @param isInitialized
     * @return
     */
    public static Class<?> loadClass(String className , boolean isInitialized){
        Class<?> clazz;
        try{
            clazz = Class.forName(className,isInitialized,getClassLoader());
        }catch (ClassNotFoundException e) {
            LOGGER.error("class load failure",e);
            throw new RuntimeException(e);
        }
        return clazz;
    }

    public static Class<?> loadClass(String className){
        return loadClass(className,false);
    }

    public static Set<Class<?>> getClassSet(String className){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        //Enumeration<>;
        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet,String packagePath,String packageName){
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
            }
        });

        for(File file:files){
            String fileName = file.getName();
            if(file.isFile()){
                String className = fileName.substring(0,fileName.lastIndexOf("."));
                if(StringUtils.isNotEmpty(packageName)){
                    className = packageName + "." + className;
                }
                doAddClass(classSet,className);
            }else{
                String subPackagePath = fileName;
                if(StringUtils.isNotEmpty(packageName)){
                    subPackagePath = packagePath + "/" + subPackagePath;
                }
                String subPackageName = fileName;
                if(StringUtils.isNotEmpty(packageName)){
                    subPackageName = packageName + "." + subPackageName;
                }
                addClass(classSet,subPackagePath,subPackageName);
            }
        }
    }

    private static void doAddClass(Set<Class<?>> classSet,String className){
        Class<?> clazz = loadClass(className,false);
        classSet.add(clazz);
    }

}
