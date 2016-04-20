package pu.study.framework.helper;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import pu.study.framework.bean.Handler;
import pu.study.framework.bean.Request;
import pu.study.framework.annotation.Action;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by project on 2016/4/19.
 */
public class ControllerHelper {
    private final static Map<Request,Handler> ACTION_MAP = new HashMap<Request,Handler>();

    static{
        //获取所有Controller注解的类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if(CollectionUtils.isNotEmpty(controllerClassSet)){
            for(Class<?> controllerClass:controllerClassSet){
                //获取类中所有的方法
                Method[] methods = controllerClass.getMethods();
                if(ArrayUtils.isNotEmpty(methods)){
                    for(Method method:methods){
                        //如果方法包含Action注解
                        if(method.isAnnotationPresent(Action.class)){
                            //从注解中获得Action配合的value的值，也就是URL
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            //验证URL规则
                            if(mapping.matches("\\w+:/\\w*")){
                                String[] array = mapping.split(":");
                                if(ArrayUtils.isNotEmpty(array) && array.length==2){
                                    //获得请求方法和路径
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
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
