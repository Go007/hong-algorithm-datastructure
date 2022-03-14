package com.hong.test;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 王宏
 * @email hong.wang8@amh-group.com
 * @date: 2022/2/26 16:41
 * @version: 1.0
 */
public class StringTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "", " ", "1", "1.0", "1.00", "1.01", "1.010", "1.0100", "0", "0.0", "0.00", "0.01", "0.0100", "1.230", "1.20300");
        Map<String, String> map = new HashMap<>(list.size());
        list.forEach(s -> map.put(s, subZeroAndDot1(s)));
        map.entrySet().forEach(e -> System.out.println(e.getKey() + "->" + e.getValue()));
    }

    /**
     * 使用正则表达式去掉小数点后多余的0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (StringUtils.isEmpty(s) || s.indexOf(".") == -1) {
            return s;
        }

        s = s.replaceAll("0+?$", "");//去掉多余的0
        s = s.replaceAll("[.]$", "");//如最后一位是.则去掉

        return s;
    }

    ///////////////////////////////////////////////////////
    private static String subZeroAndDot1(String s) {
        if (StringUtils.isEmpty(s) || !NumberUtils.isCreatable(s) || !s.contains(".") || s.lastIndexOf("0") != s.length() - 1) {
            return s;
        }

        String[] split = s.split("\\.");
        String integerPart = split[0];
        String pointPart = split[1];
        int pointPartLen = pointPart.length();
        // 最后一个非0的字符的索引位置
        Integer lastNotZeroCharIndex = null;
        char[] chars = pointPart.toCharArray();
        for (int i = pointPartLen - 1; i >= 0; i--) {
            // 倒序遍历小数点后的char[],遇到第一个非0的值则中断循环;
            if ('0' != chars[i]) {
                break;
            }

            lastNotZeroCharIndex = i;
        }

        // 确实有多余的0
        if (null != lastNotZeroCharIndex) {
            pointPart = pointPart.substring(0, lastNotZeroCharIndex);
        }

        return StringUtils.isEmpty(pointPart) ? integerPart : integerPart + "." + pointPart;
    }
}
