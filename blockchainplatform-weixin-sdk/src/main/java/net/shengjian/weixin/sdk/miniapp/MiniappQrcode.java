package net.shengjian.weixin.sdk.miniapp;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MiniappQrcode implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Object> qrCodeMap = new HashMap<>();
    private Map<String, Integer> lineColorMap = null;


    public MiniappQrcode() {
    }

    public void setScene(String scene) {
        qrCodeMap.put("scene", scene);
    }

    public void setPage(String page) {
        qrCodeMap.put("page", page);
    }

    public void setWidth(Integer width) {
        qrCodeMap.put("width", width);
    }

    public void setAutoColor(Boolean autoColor) {
        qrCodeMap.put("auto_color", autoColor);
    }

    public void setHyaline(Boolean hyaline) {
        qrCodeMap.put("is_hyaline", hyaline);
    }

    public void setLineColorR(Integer lineColorR) {
        if (lineColorMap == null) {
            lineColorMap = new HashMap<>();
        }
        lineColorMap.put("r", lineColorR);
    }

    public void setLineColorG(Integer lineColorG) {
        if (lineColorMap == null) {
            lineColorMap = new HashMap<>();
        }
        lineColorMap.put("g", lineColorG);
    }

    public void setLineColorB(Integer lineColorB) {
        if (lineColorMap == null) {
            lineColorMap = new HashMap<>();
        }
        lineColorMap.put("b", lineColorB);
    }

    public Map<String, Object> getQrCodeMap() {
        if (lineColorMap != null) {
            qrCodeMap.put("line_color", lineColorMap);
        }
        return qrCodeMap;
    }


}
