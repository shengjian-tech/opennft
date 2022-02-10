package net.shengjian.frame.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Date;

/**
 * 全局处理jackson解析日期字符串,不需要在字段上加上日期格式的注解了.
 * 自动分析字符串格式,转化成Date对象
 */
public class JacksonDateDeserializer extends DateDeserializers.DateDeserializer {
    @Override
    public Date deserialize(JsonParser p, DeserializationContext context) throws IOException {
        if (p == null) {
            return super.deserialize(p, context);
        }
        String text = p.getText();
        text = text.trim();
        if (StringUtils.isBlank(text)) {
            return super.deserialize(p, context);
        }
        Date date = DateUtils.convertString2Date(text);
        if (date != null) {
            return date;
        }

        return super.deserialize(p, context);

    }
}
