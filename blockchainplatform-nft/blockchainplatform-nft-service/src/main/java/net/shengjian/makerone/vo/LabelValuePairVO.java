package net.shengjian.makerone.vo;

import java.io.Serializable;

/**
 * @descriptions: label-value键值对
 * @author: YSK
 * @date: 2021/12/24 10:20
 * @version: 1.0
 */
public class LabelValuePairVO implements Serializable {
    private String label;
    private String value;

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
