package pu.study.framework.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pu.study.framework.bean.Handler;
import pu.study.framework.bean.Request;
import pu.study.framework.annotation.Action;
import pu.study.framework.util.JSONUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by project on 2016/4/19.
 */
public class ControllerHelper {
    private final static Map<Request,Handler> ACTION_MAP = new HashMap<Request,Handler>();
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerHelper.class);
    static{
        //获取所有Controller注解的类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtils.isNotEmpty(controllerClassSet)){
            LOGGER.debug("获得所有controller："+JSONUtil.toJson(controllerClassSet));
            for(Class<?> controllerClass:controllerClassSet){
                //获取类中所有的方法
                Method[] methods = controllerClass.getMethods();
                if(ArrayUtils.isNotEmpty(methods)){
                    LOGGER.debug("获得所有方法："+JSONUtil.toJson(methods));
                    for(Method method:methods){
                        //如果方法包含Action注解
                        if(method.isAnnotationPresent(Action.class)){
                            //从注解中获得Action配合的value的值，也就是URL
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            LOGGER.debug("注解中设置的value为:"+mapping);
                            //验证URL规则
                            if(mapping.matches("\\w+:/\\w*")){
                                LOGGER.debug("符合验证规则");
                                String[] array = mapping.split(":");
                                if(ArrayUtils.isNotEmpty(array) && array.length==2){
                                    //获得请求方法和路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    LOGGER.debug("设置的请求方法为："+requestMethod);
                                    LOGGER.debug("设置的请求的路径为："+requestPath);
                                    Request request = new Request(requestMethod,requestPath);
                                    Handler handler = new Handler(controllerClass,method);
                                    ACTION_MAP.put(request,handler);
                                }
                            }
                        }
                    }
                }
            }
        }
        LOGGER.debug("获得所有Action:"+JSONUtil.toJson(ACTION_MAP));
    }

    /**
     * 获取handler
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getHandler(String requestMethod,String requestPath){
        Request request = new Request(requestMethod,requestPath);
        return ACTION_MAP.get(request);
    }
}
