package pu.study.framework.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
        LOGGER.debug("------------------加载类-----------------");
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
            LOGGER.debug("加载Class："+className+"...");
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

    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        //Enumeration<>;
        try{
            Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".","/"));
            while(urls.hasMoreElements()){
                URL url = urls.nextElement();
                if(null!=url){
                    String protocal = url.getProtocol();
                    if(protocal.equals("file")){
                        String packagePath = url.getPath().replace("%20"," ");
                        LOGGER.debug("获得为file进入路径"+packagePath);
                        addClass(classSet,packagePath,packageName);
                    }else if(protocal.equals("jar")){
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if(null!=jarURLConnection){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if(null!=jarFile){
                                Enumeration<JarEntry> jarEntryEnumeration = jarFile.entries();
                                while(jarEntryEnumeration.hasMoreElements()){
                                    JarEntry jarEntry = jarEntryEnumeration.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if(jarEntryName.equals("class")){
                                        String className = jarEntryName.substring(0,jarEntryName.lastIndexOf("."))
                                                .replaceAll("/",".");
                                        doAddClass(classSet,className);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch (Exception e){
            LOGGER.error("获取类集合失败",e);
            throw new RuntimeException(e);
        }
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
                LOGGER.debug("获得类名:"+className);
                if(StringUtils.isNotEmpty(packageName)){
                    className = packageName + "." + className;
                }
                doAddClass(classSet,className);
            }else{
                String subPackagePath = fileName;
                LOGGER.debug("获得子路径:"+subPackagePath);
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
