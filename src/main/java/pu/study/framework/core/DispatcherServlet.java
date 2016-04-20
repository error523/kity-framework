package pu.study.framework.core;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import pu.study.framework.bean.Data;
import pu.study.framework.bean.Handler;
import pu.study.framework.bean.Param;
import pu.study.framework.bean.View;
import pu.study.framework.helper.BeanHelper;
import pu.study.framework.helper.ConfigHelper;
import pu.study.framework.helper.ControllerHelper;
import pu.study.framework.util.CodecUtil;
import pu.study.framework.util.JSONUtil;
import pu.study.framework.util.ReflectionUtil;
import pu.study.framework.util.StreamUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by project on 2016/4/20.
 */
@WebServlet(urlPatterns = "/*",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet{
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化框架自定义的那些类，具体写在HelperLoader中
        HelperLoader.init();
        //获取servlet上下文以注册servlet
        ServletContext servletContext = servletConfig.getServletContext();
        //注册处理JSP文件的servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
        //注册处理静态文件的servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppBaseAssetPath()+"*");
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获得请求方法和路径
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();
        //根据请求的方法路径获得Action对象
        Handler handler = ControllerHelper.getHandler(requestMethod,requestPath);
        if(handler!=null){
            //获取Controller类以及bean实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            Map<String,Object> paramMap = new HashMap<String,Object>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()){
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName,paramValue);
            }
            String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if(StringUtils.isNotEmpty(body)){
                String[] params = StringUtils.split(body,"&");
                if(ArrayUtils.isNotEmpty(params)){
                    for(String param:params){
                        String[] array = StringUtils.split(param,"=");
                        if(ArrayUtils.isNotEmpty(array)&&array.length==2){
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName,paramValue);
                        }
                    }
                }
            }
            Param param = new Param(paramMap);
            //获得action方法
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokMethod(controllerBean,actionMethod,param);
            if(result instanceof View){
                //返回JSP页面
                View view = (View) result;
                String path = view.getPath();
                if(StringUtils.isNotEmpty(path)){
                    resp.sendRedirect(req.getContextPath()+path);
                }else{
                    Map<String ,Object> model = view.getModel();
                    for(Map.Entry<String,Object> entry:model.entrySet()){
                        req.setAttribute(entry.getKey(),entry.getValue());
                    }
                    req.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(req,resp);
                }
            }else if(result instanceof Data){
                Data data = (Data) result;
                Object model = data.getModel();
                if(model!=null){
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    String json = JSONUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }

    }

}
