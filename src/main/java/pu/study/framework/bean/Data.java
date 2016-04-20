package pu.study.framework.bean;

import java.util.Map;

/**
 * Created by project on 2016/4/20.
 */
public class Data {
    /**
     * 模型数据
     */
    private Map<String,Object> model;

    public Data(Map<String, Object> model) {
        this.model = model;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
