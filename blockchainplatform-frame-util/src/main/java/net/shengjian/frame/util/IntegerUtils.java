package net.shengjian.frame.util;

/**
 * 数值比较
 *
 * @author mjz
 */

public class IntegerUtils {

    /**
     * 比较两个值是否相等
     *
     * @param val1
     * @param val2
     * @return
     */
    public static boolean compreVal(Integer val1, Integer val2) {
        if (val1 == null || val2 == null) {
            return false;
        }
        return val1.equals(val2);
    }

    /**
     * 判断一个值是不是有小数，返回/100之后的数字字符串<br/>
     * 例如：500返回5， 1010返回10.1
     *
     * @param val *100之后的数字
     * @return
     */
    public static String checkValPoint(Integer val) {
        if (val == null) {
            return null;
        }
        if (val % 100 == 0) {//余数等于0
            return String.valueOf((int) val / 100);
        } else {
            return String.valueOf(val / 100.0);
        }
    }

    /**
     * 判断一个值是不是有小数，判断是否是精确到2位的数字
     *
     * @param val
     * @return
     */
    public static String checkValPoint(String val) {
        if (val == null) {
            return null;
        }
        if (val.split("\\.")[1].length() == 2) {
            //两位小数
            return String.valueOf(Double.valueOf(val) * 10);
        } else {
            return String.valueOf(((int) (Double.valueOf(val) * 10)));
        }
    }
}
