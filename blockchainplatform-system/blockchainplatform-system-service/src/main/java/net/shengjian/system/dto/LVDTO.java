package net.shengjian.system.dto;

import java.io.Serializable;

/**
 * @descriptions: label and value
 * @author: YSK
 * @date: 2021/7/6 11:41
 * @version: 1.0
 */
public class LVDTO implements Serializable {
    /**
     * 标签
     */
    private String label;
    /**
     * 值
     */
    private String value;

    public LVDTO() {
    }

    public LVDTO(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
