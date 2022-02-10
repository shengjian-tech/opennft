package net.shengjian.weixin.sdk.miniapp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MiniappTemplateMessage implements Serializable {
    private static final long serialVersionUID = 1L;


    private Map<String, Object> templateMessageMap = new HashMap<>();
    private Map<String, Object> dataMap = null;


    public MiniappTemplateMessage() {
    }

    public Map<String, Object> getTemplateMessageMap() {
        if (dataMap != null) {
            templateMessageMap.put("data", dataMap);
        }
        return templateMessageMap;
    }

    public void setTouser(String touser) {
        templateMessageMap.put("touser", touser);
    }

    public void setTemplateId(String templateId) {
        templateMessageMap.put("template_id", templateId);
    }

    public void setPage(String page) {
        templateMessageMap.put("page", page);
    }

    public void setFormId(String formId) {
        templateMessageMap.put("form_id", formId);
    }

    public void setEmphasisKeyword(String emphasisKeyword) {
        templateMessageMap.put("emphasis_keyword", emphasisKeyword);
    }

    public void addData(String key, String value) {
        if (dataMap == null) {
            dataMap = new HashMap<>();
        }
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("value", value);
        dataMap.put(key, valueMap);
    }


}
