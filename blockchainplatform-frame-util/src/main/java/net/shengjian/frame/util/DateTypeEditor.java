package net.shengjian.frame.util;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期编辑器
 * <p>
 * 根据日期字符串长度判断是长日期还是短日期。只支持yyyy-MM-dd，yyyy-MM-dd HH:mm:ss两种格式。
 * 扩展支持yyyy,yyyy-MM日期格式
 */
public class DateTypeEditor extends PropertyEditorSupport {

    /**
     * Format the Date as String, using the specified DateFormat.
     */
    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value) : "");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        text = text.trim();
        if (StringUtils.isBlank(text)) {
            setValue(null);
            return;
        }
        Date date = DateUtils.convertString2Date(text);
        if (date != null) {
            setValue(date);
        }
    }
}
