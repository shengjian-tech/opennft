package net.shengjian.makerone.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @descriptions: 日期参数
 * @author: YSK
 * @date: 2021/12/24 14:42
 * @version: 1.0
 */
public class ParmaDateVO implements Serializable {
    /**
     * 开始时间
     */
    private Date startDateTime;

    /**
     * 结束时间
     */
    private Date endDateTime;

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }
}
