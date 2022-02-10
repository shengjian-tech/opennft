package net.shengjian.frame.util.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * message/messageResources内容读取类
 *
 * @author zhengyongsheng
 */
public class ResourceUtils {
    private static final Logger logger = LoggerFactory.getLogger(ResourceUtils.class);

    private static PropertyResourceBundle propertyResourceBundle;

    static {
        propertyResourceBundle = (PropertyResourceBundle) ResourceBundle.getBundle("message/messageResources",
                Locale.getDefault());
    }

    private ResourceUtils() {
        throw new IllegalAccessError("工具类不能实例化");
    }

    /**
     * 根据key获得对应的value
     *
     * @param strPropertyName key
     * @return String
     */
    public static String getString(String strPropertyName) {
        try {
            return propertyResourceBundle.getString(strPropertyName);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return strPropertyName;
        }
    }

    public static String getString(String strPropertyName, Object... obj) {
        String str = propertyResourceBundle.getString(strPropertyName);
        if (str == null) {
            return strPropertyName;
        }
        return MessageFormat.format(str, obj);
    }

    public static PropertyResourceBundle getBundle() {
        return propertyResourceBundle;
    }

    /*
     * public static void main(String[] args) {
     * System.out.print(Resource.getString("jiagou.com")); }
     */
}
