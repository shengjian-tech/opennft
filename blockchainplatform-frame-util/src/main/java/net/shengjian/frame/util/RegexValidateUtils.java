package net.shengjian.frame.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 *
 * @author springrain<Auto generate>
 * @version 2013-03-19 11:08:15
 * @see RegexValidateUtils
 */

public class RegexValidateUtils {
    private static final Logger logger = LoggerFactory.getLogger(RegexValidateUtils.class);

    private RegexValidateUtils() {
        throw new IllegalAccessError("工具类不能实例化");
    }


    /**
     * 验证字符串是否含有非法字符 含有：返回false 否则：返回true
     *
     * @param name
     * @return
     */
    public static boolean checkNameContainIllegalCharacter(String name) {
        boolean flag = false;
        String regex = "([a-z]|[A-Z]|[0-9]|[\\u4e00-\\u9fa5])+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    /**
     * 检查手机号格式
     * <p>
     * 三大运营商的号段汇总： 中国移动：134、135、136、137、138、139、150、151、152、157(TD)、
     * 158、159、182、183、184、187、178、188、147（数据卡号段） 、 1705（虚拟运营商移动号段）
     * 中国联通：130、131、132、145(数据卡号段)155、156、176、185、186、 1709（虚拟运营商联通号段）
     * 中国电信：133、153、177、180、181、189、（1349卫通）、1700（虚拟运营商电信号段）
     *
     * @param mobile
     * @return
     */
    public static boolean checkMobile(String mobile) {
        boolean flag = false;
        Pattern regex = Pattern.compile("^1[34578]\\d{9}$");
        Matcher matcher = regex.matcher(mobile);
        flag = matcher.find();
        return flag;
    }

    /**
     * 检查邮箱格式
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        if (StringUtils.isBlank(email)) {
            return flag;
        }
        Pattern regex = Pattern.compile("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$");
        Matcher matcher = regex.matcher(email);
        flag = matcher.find();
        return flag;
    }

    /**
     * 检查网址链接
     *
     * @param url
     * @return
     */
    public static boolean checkURL(String url) {
        boolean flag = false;
        Pattern regex = Pattern.compile(
                "^(HTTP|FTP|HTTPS|http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:\\/~\\+#]*[\\w\\-\\@?^=%&amp;\\/~\\+#])?$");
        Matcher matcher = regex.matcher(url);
        flag = matcher.find();
        return flag;
    }

    /***********************************
     * 身份证验证开始
     ****************************************/
    /**
     * 身份证号码验证 1、号码的结构 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，
     * 八位数字出生日期码，三位数字顺序码和一位数字校验码。 2、地址码(前六位数）
     * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。 3、出生日期码（第七位至十四位）
     * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。 4、顺序码（第十五位至十七位）
     * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号， 顺序码的奇数分配给男性，偶数分配给女性。 5、校验码（第十八位数）
     * （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
     * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8
     * 7 6 5 4 3 2
     */
    /**
     * 功能：身份证的有效验证
     *
     * @param IDStr 身份证号
     * @return 有效：返回"" 无效：返回String信息
     * @throws ParseException
     */
    public static String checkIDCard(String IDStr) throws ParseException {
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
        String Ai = "";
        if (StringUtils.isBlank(IDStr)) {
            return "身份证号为空";
        }
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";
            return errorInfo;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return errorInfo;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            return errorInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份证生日不在有效范围。";
                return errorInfo;
            }
        } catch (NumberFormatException e) {
            logger.error(e.getMessage(), e);
        } catch (java.text.ParseException e) {
            logger.error(e.getMessage(), e);
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            return errorInfo;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            return errorInfo;
        }
        // =====================(end)=====================

        // ================ 地区码时候有效 ================
        Hashtable h = GetAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";
            return errorInfo;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return errorInfo;
            }
        } else {
            return "";
        }
        // =====================(end)=====================
        return "";
    }

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */

    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile(
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /***********************************
     * 身份证验证结束
     ****************************************/

    /**
     * 判断昵称是否包含emoji表情。若包含则去掉。
     *
     * @param nickName
     * @return
     */
    public static String formatName(String nickName) {
        if (!containsEmoji(nickName)) {
            return "";// 如果全部是特殊字符返回空
        }
        // 到这里铁定包含
        StringBuilder buf = null;

        int len = nickName.length();

        for (int i = 0; i < len; i++) {
            char codePoint = nickName.charAt(i);
            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(nickName.length());
                }

                buf.append(codePoint);
            } else {
            }
        }

        if (buf == null) {
            return nickName;// 如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return nickName;
            } else {
                return buf.toString();
            }
        }

    }

    public static boolean containsEmoji(String source) {
        if (StringUtils.isBlank(source)) {
            return false;
        }

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                // do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    public static String getQuartzTimeStr(Date date) {
        StringBuffer buffer = new StringBuffer();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int second = c.get(Calendar.SECOND);
        int minute = c.get(Calendar.MINUTE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        buffer.append(second).append(" ");
        buffer.append(minute).append(" ");
        buffer.append(hour).append(" ");
        buffer.append(day).append(" ");
        buffer.append(month + 1).append(" ");
        buffer.append("?").append(" ");
        buffer.append(year);
        return buffer.toString();
    }


    // 验证文件后缀
    // Pattern p =
    // Pattern.compile(".+\\.js$",Pattern.CASE_INSENSITIVE);//不区分大小写的,默认只有一个参数时是区分大小写
    // System.out.println(p.matcher("dads.jS").matches());

    /**
     * 对一字符串进行正则验证 验证一个字符串中长度必须大于6位，同时必须包含至少一个字符和一个数值
     *
     * @param str 待验证的字符串
     * @return 通过验证时返回null 如果验证出现问题，会返回对应的失败原因
     * @throws Exception
     */
    public static String validateString(String str) throws Exception {
        // 验证长度
        if (validateLength(str) == null) {
            // 验证字符元素
            return isContainNumber(str);
        } else {
            return validateLength(str);
        }

    }

    /**
     * 验证content中必须同时存在数字和字母
     *
     * @param content
     * @return 通过验证时，返回null 否则返回对应提示
     * @throws Exception
     */
    public static String isContainNumber(String content) throws Exception {
        String message = "必须包含数字和字母";
        String restring = "^.*(([a-zA-Z]+.*[0-9]+)|([0-9]+.*[a-zA-Z]+)).*$"; // 必须六位以上,包含数字和字母
        Pattern pattern = Pattern.compile(restring);
        if (pattern.matcher(content).matches()) {
            return null;
        }
        return message;
    }

    /**
     * 验证content的长度必须大于等六位
     *
     * @param content
     * @return 通过验证时，返回null 否则返回对应提示
     * @throws Exception
     */
    public static String validateLength(String content) throws Exception {
        String message = "必须六位以上";
        String restring = "^.{6,}$";
        Pattern pattern = Pattern.compile(restring);
        if (pattern.matcher(content).matches()) {
            return null;
        }
        return message;
    }

    /**
     * 如果字符串url是否以指定的数组的任一字符串结尾
     *
     * @param url     指定字符串
     * @param noFomat 格式集[".js",".html"] <<主要是用于文件格式的验证，必须以"."开头加后缀名 如： .js >>
     * @return url不是以指定的数组的任一字符串结尾 返回 true 否则返回false
     */
    public static boolean validateUrl(String url, String[] noFomat) {
        // 正则非
        String endStr = "";
        // 为空时返回true
        if (noFomat == null || noFomat.length - 0 == 0 || url == null) {
            return true;
        } else {
            for (int i = 0; i < noFomat.length; i++) {
                if (i - 0 == 0) {
                    endStr = "((" + noFomat[i] + ")";
                } else {
                    endStr = endStr + "|(" + noFomat[i] + ")";
                }

                if (i - noFomat.length + 1 == 0) {
                    endStr = endStr + ")$";
                }
            }
        }
        // 定义正则
        String reg = "^.*";
        // 验证文件后缀
        // 其中参数意义：不区分大小写的,默认只有一个参数时是区分大小写
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(reg + endStr,
                java.util.regex.Pattern.CASE_INSENSITIVE);
        return !p.matcher(url).matches();
    }

    /**
     * 验证指定的字符串是不是符点型数值
     *
     * @param value 某指定的字符串
     * @return 字符串符合数值格式时返回true, 否则返回false
     */
    private static String floatRestring = "^-?(\\d)+((.\\d+)|(\\d)*)$";
    private static Pattern flatPattern = Pattern.compile(floatRestring);

    public static boolean isFloat(String value) {

        if (flatPattern.matcher(value).matches()) {
            return true;
        }
        return false;
    }

    /**
     * 获取 order by 开始位置
     *
     * @param orderbysql
     * @return
     */
    private static String orderByRegStr = "(?i)\\s(order)\\s+by\\s";
    private static Pattern orderByPattern = Pattern.compile(orderByRegStr);

    public static int getOrderByIndex(String orderbysql) {
        int index = -1;
        if (StringUtils.isBlank(orderbysql)) {
            return index;
        }

        Matcher matcher = orderByPattern.matcher(orderbysql);
        if (matcher.find()) {
            index = matcher.start();
        }
        return index;
    }

    /**
     * 获取 group by 开始位置
     *
     * @param groupbysql
     * @return
     */
    private static String groupByRegStr = "(?i)\\s(group)\\s+by\\s";
    private static Pattern groupByPattern = Pattern.compile(groupByRegStr);

    public static int getGroupByIndex(String groupbysql) {
        int index = -1;
        if (StringUtils.isBlank(groupbysql)) {
            return index;
        }

        Matcher matcher = groupByPattern.matcher(groupbysql);
        if (matcher.find()) {
            index = matcher.start();
        }
        return index;
    }

    /**
     * 获取语句中表名
     * 第一个是符合的整体数据,第二个是表名
     *
     * @param updatesql
     * @return
     */
    private static String updateTableNameRegStr = "(?i)^\\s*update\\s+(\\w+)\\s+set\\s";
    private static Pattern updateTableNamePattern = Pattern.compile(updateTableNameRegStr);

    public static String[] getUpdateTableName(String updatesql) {
        if (StringUtils.isBlank(updatesql)) {
            return null;
        }

        Matcher matcher = updateTableNamePattern.matcher(updatesql);
        if (!matcher.find()) {
            return null;
        }

        String[] result = new String[2];
        result[0] = matcher.group(0);
        result[1] = matcher.group(1);

        return result;
    }

    /**
     * 获取语句中表名
     * 第一个是符合的整体数据,第二个是表名
     *
     * @param updatesql
     * @return
     */
    private static String deleteableNameRegStr = "(?i)^\\s*delete\\s+from\\s+(\\w+)\\s+where\\s";
    private static Pattern deleteTableNamePattern = Pattern.compile(deleteableNameRegStr);

    public static String[] getDeleteTableName(String deletesql) {
        if (StringUtils.isBlank(deletesql)) {
            return null;
        }

        Matcher matcher = deleteTableNamePattern.matcher(deletesql);
        if (!matcher.find()) {
            return null;
        }

        String[] result = new String[2];
        result[0] = matcher.group(0);
        result[1] = matcher.group(1);

        return result;
    }

    /**
     * 获取 Select 语句中 From 开始位置
     * 存在bug,如果where后有 id in (select id from table) 会解析失败.
     *
     * @param sql
     * @return
     */
/*
    //select id1,(select (id2) from t1 where id=2) _s FROM table select的子查询 _s中的 id2还有括号,才会出现问题,建议使用CountFinder处理分页语句
    // 未知原因,java解析卡死
    ////private static String selectFromIndexRegStr = "(?i)(^\\s*select)(.+?\\(.+?\\))*.*?(from)";
    private static String selectFromIndexRegStr = "(?i)(^\\s*select)(\\(.*?\\)|[^()]+)*?(from)";
    private static Pattern selectFromIndexPattern = Pattern.compile(selectFromIndexRegStr, Pattern.CASE_INSENSITIVE);
    public static int getSelectFromIndex(String sql) {
        int index = -1;
        if (StringUtils.isBlank(sql)) {
            return index;
        }

        Matcher matcher = selectFromIndexPattern.matcher(sql);
        if (matcher.find()) {
            index = matcher.end() - 4;
        }
        return index;
    }
*/
    /**
     * 获取 Select 语句中 From 开始位置
     *
     * @return
     */
    public static int getSelectFromIndex(String sql) {
        int index = -1;
        if (StringUtils.isBlank(sql)) {
            return index;
        }

        String s = replaceFrom(sql.toLowerCase());

        int startIndex = s.indexOf(" from ");
        int lastIndex = s.lastIndexOf(" from ");
        if (startIndex - lastIndex != 0) {
            return index;
        }
        index = startIndex;
        return index;
    }


    private static  Pattern pt = Pattern.compile("\\(([\\s\\S]+?)\\)", Pattern.CASE_INSENSITIVE);
    private static String replaceFrom(String str) {
        Matcher matcher = pt.matcher(str);
        while (matcher.find()) {
            String group = matcher.group(1);
            String t1 = group.replace(" from ", " abcd ");
            str = str.replace(group, t1);
        }
        return str;
    }

/*
	public static void main(String[] args) {
		// System.out.println(RegexValidateUtils.isFloat("-012416545.000"));
		String countSql =  "Select id,(select 1 From t1 where id=1) id1,(select 2 from t2 WHERE id=2) id2,(id3) FROM table WHERE id=id and id in(SELECT id from t4 where name=4) and name in(select name from t5 where name=6) and name=c";
       // String countSql = "123456789";

        System.out.println(countSql.substring(getSelectFromIndex(countSql)));

	}

*/


}
