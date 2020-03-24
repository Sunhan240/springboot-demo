package com.hansun.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author  : sunhan
 * @date : 2020/3/4 23:18
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private StringUtils(){}

    public static String parseString(Object obj){
        if (null == obj || "".equals(obj)) {
            return "";
        }
        return obj.toString();
    }

    public static boolean isNumber(String str) {
        int i = str.length();
        for (; --i > 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static int getHashValue(Object v, int length) {
        v = parseString(v);
        return Math.abs(v.hashCode()) % length;
    }

    /**
     * 按照标签如：[link][[表情]nk]截取标签中的内容
     *
     * @param str 需要截取的字符串
     * @param tag 标签名
     * @return List集合
     */
    public static List<String> getTag(String str, String tag) {
        List<String> items = new ArrayList<>();
        String regex = "\\[" + tag + "](.+?)\\[/" + tag + "]";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        while (m.find()) {
            String itemTemp = m.group();
            String item = itemTemp.substring(tag.length() + 2, itemTemp.length() - tag.length() - 3);
            items.add(item);
        }
        return items;
    }

    /**
     * 获取xml格式包装的标签内容
     *
     * @param str 字符
     * @param tag 标签
     * @return content
     */
    public static List<String> getXmlTag(String str, String tag) {
        List<String> items = new ArrayList<>();
        String regex = "<" + tag + ">(.+?)</" + tag + ">";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        while (m.find()) {
            String itemTemp = m.group();
            String item = itemTemp.substring(tag.length() + 2, itemTemp.length() - tag.length() - 3);
            items.add(item);
        }
        return items;
    }

    /**
     * 按照标签如：[link][[表情]nk]移除标签及标签中的内容
     *
     * @param str 需要移除标签的字符串
     * @param tag 标签名
     * @return 移除后的str
     */
    public static String removeTag(String str, String tag) {
        String regex = "\\[" + tag + "](.+?)\\[/" + tag + "]";
        return str.replaceAll(regex, "");
    }

    public static String deleteLine(String str) {
        if (isEmpty(str)) {
            return str;
        }
        str = str.replaceAll("\r\n", "\n").
                replaceAll("\n", "");
        return str;
    }

    public static String delRepeatLine(String str) {
        if (isEmpty(str)) {
            return str;
        }
        str = str.replace("\r", "\n")
                .replaceAll("\r\n|\n\n", "");
        return str;
    }



}
