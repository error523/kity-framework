package pu.study.framework.bean;

import pu.study.framework.util.CastUtil;

import java.util.Map;

/**
 * Created by project on 2016/4/19.
 */
public class Param {
    private Map<String,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public long getLong(String paramName){
        return CastUtil.castLong(paramMap.get(paramName));
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
}
