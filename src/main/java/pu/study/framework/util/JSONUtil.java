package pu.study.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by project on 2016/4/20.
 */
public final class JSONUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将POJO 转换成JSON
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj){
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error("convert POJO to JSON fialure",e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将JSON 对象转换成POJO
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json,Class<T> type){
        T pojo = null;
        try {
            pojo =  OBJECT_MAPPER.readValue(json,type);
            if(pojo==null){
                LOGGER.error("转换失败，转换对象为空");
                throw new RuntimeException("转换失败，转换对象为空");
            }
        } catch (IOException e) {
            LOGGER.error("convert JSON to POJO failure",e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
