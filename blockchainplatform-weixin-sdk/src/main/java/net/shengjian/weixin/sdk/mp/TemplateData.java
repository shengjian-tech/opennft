package net.shengjian.weixin.sdk.mp;

import net.shengjian.frame.util.JsonUtils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 模板消息数据对象
 * <p>
 * https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Template_Message_Interface.html
 * #description 模板消息数据对象
 */
public class TemplateData implements Serializable {
    private static final long serialVersionUID = 8038149984818112449L;

    private String touser;
    private String template_id;
    private String url;
    private TemplateItem data;
    private MiniProgramData miniprogram;

    private TemplateData() {
        this.data = new TemplateItem();
    }

    public static TemplateData New() {
        return new TemplateData();
    }

    public String getTouser() {
        return touser;
    }

    public TemplateData setTouser(String touser) {
        this.touser = touser;
        return this;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public TemplateData setTemplate_id(String template_id) {
        this.template_id = template_id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public TemplateData setUrl(String url) {
        this.url = url;
        return this;
    }

    public TemplateItem getData() {
        return data;
    }

    public TemplateData add(String key, String value, String color) {
        data.put(key, new Item(value, color));
        return this;
    }

    public TemplateData add(String key, String value) {
        data.put(key, new Item(value));
        return this;
    }

    public MiniProgramData getMiniprogram() {
        return miniprogram;
    }

    public TemplateData setMiniprogram(String appid, String pagepath) {
        this.miniprogram = new MiniProgramData(appid, pagepath);
        return this;
    }

    /**
     * 直接转化成jsonString
     *
     * @return {String}
     */
    public String build() {
        return JsonUtils.writeValueAsString(this);
    }

    public class TemplateItem extends HashMap<String, Item> {

        private static final long serialVersionUID = -3728490424738325020L;

        public TemplateItem() {
        }

        public TemplateItem(String key, Item item) {
            this.put(key, item);
        }
    }

    public class Item {
        private Object value;
        private String color;

        public Item(Object value) {
            this(value, "#999");
        }

        public Item(Object value, String color) {
            this.value = value;
            this.color = color;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    public class MiniProgramData {
        private String appid;
        private String pagepath;

        public MiniProgramData(String appid, String pagepath) {
            this.appid = appid;
            this.pagepath = pagepath;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPagepath() {
            return pagepath;
        }

        public void setPagepath(String pagepath) {
            this.pagepath = pagepath;
        }

    }
}
