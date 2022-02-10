package net.shengjian.makerone.vo;

import java.io.Serializable;

/**
 * @descriptions: label-value键值对
 * @author: YSK
 * @date: 2021/12/24 10:20
 * @version: 1.0
 */
public class LabelValuePairVO2 implements Serializable {
    private String label;
    private Integer value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
