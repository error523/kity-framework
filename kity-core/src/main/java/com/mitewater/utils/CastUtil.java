package com.mitewater.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 类型转换工具类
 * Created by phq on 2016/4/12.
 * varsion  1.0.0
 */
public final class CastUtil {

    /**
     * 转换为String 需要提供默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj,String defaultValue) {
        return obj != null? String.valueOf(obj):defaultValue;
    }

    /**
     * 转换为String 默认为空
     * @param obj
     * @return
     */
    public static String castString(Object obj) {
        return castString(obj,"");
    }

    /**
     * 转换为double，提供默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static double castDouble(Object obj,double defaultValue) {
        double doubleValue = defaultValue;
        if(obj != null){
            String strValue=castString(obj);
            if(StringUtils.isNoneEmpty(strValue)){
                try{
                    doubleValue = Double.parseDouble(strValue);
                }catch(NumberFormatException e){
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转换为double，默认值为0
     * @param obj
     * @return
     */
    public static double castDouble(Object obj){
        return castDouble(obj,0);
    }

    /**
     * 转换为long型，提供默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static long castLong(Object obj,long defaultValue){
        long longValue = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if(StringUtils.isNotEmpty(strValue)){
                try{
                    longValue = Long.parseLong(strValue);
                }catch(NumberFormatException e){
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    /**
     * 转换为long，默认值为0
     * @param obj
     * @return
     */
    public static long castLong(Object obj){
        return castLong(obj,0);
    }

    /**
     * 转换为int型，提供默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static int castInt(Object obj,int defaultValue){
        int intValue = defaultValue;
        if(obj != null){
            String strValue = castString(obj);
            if(StringUtils.isNotEmpty(strValue)){
                try{
                    intValue = Integer.parseInt(strValue);
                }catch(NumberFormatException e){
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     * 转换为int，默认值为0
     * @param obj
     * @return
     */
    public static int castInt(Object obj){
        return castInt(obj,0);
    }

    /**
     * 转换为boolean，设置默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static boolean castBoolean(Object obj,boolean defaultValue){
        boolean booleanValue = defaultValue;
        if(obj!=null){
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }

    /**
     * 转换为boolean，默认值为false
     * @param obj
     * @return
     */
    public static boolean castBoolean(Object obj){
        return castBoolean(obj,false);
    }

}
