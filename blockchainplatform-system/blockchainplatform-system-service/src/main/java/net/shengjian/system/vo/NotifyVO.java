package net.shengjian.system.vo;

import java.io.Serializable;

/**
 * @descriptions: 消息通知VO
 * @author: YSK
 * @date: 2021/9/14 13:40
 * @version: 1.0
 */
public class NotifyVO implements Serializable {
    /**
     * id
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 通知时间
     */
    private String notifyTime;
    /**
     * 状态,0未读,1已读
     */
    private Integer status;
    /**
     * 消息类型
     * 1,系统运行通知
     * 2,推广消息
     */
    private Integer type;
    /**
     * 推广消息跳转的url
     */
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(String notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
