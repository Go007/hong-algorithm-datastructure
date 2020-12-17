package com.hong.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author wanghong
 * @Date 2020/12/16 18:32
 * @Version V1.0
 **/
public class StringUtil {
    public StringUtil() {
    }

    public static int getUTF8ByteLength(String s) {
        int len = 0;

        try {
            len = s.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException var3) {

        }

        return len;
    }

    public static String toHexColorString(Integer color) {
        String s = Integer.toHexString(color);
        int len = 6 - s.length();

        for (int i = 0; i < len; ++i) {
            s = "0" + s;
        }

        return "#" + s.toUpperCase();
    }

    public static boolean isInArrayString(String arrStr, String sep, String value) {
        if (arrStr != null && sep != null && value != null) {
            String[] arr = arrStr.split(sep);
            String[] var7 = arr;
            int var6 = arr.length;

            for (int var5 = 0; var5 < var6; ++var5) {
                String s = var7[var5];
                if (s.equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isNullOrEmpty(String str) {
        if (str == null) {
            return true;
        } else if (str.length() == 0) {
            return true;
        } else {
            return str.trim().length() == 0;
        }
    }

    public static String getMD5Str(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = null;
        messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(str.getBytes("UTF-8"));
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; ++i) {
            if (Integer.toHexString(255 & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(255 & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(255 & byteArray[i]));
            }
        }

        return md5StrBuff.toString();
    }

    public static String toUnicodeString(String s, String prefix) {
        if (s != null && s.length() > 0) {
            if (prefix == null) {
                prefix = "";
            }

            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                sb.append(prefix).append(Integer.toHexString(c));
            }

            return sb.toString();
        } else {
            return "";
        }
    }

    public static String toRegexUnicodeString(String s) {
        return toUnicodeString(s, "\\x");
    }

    public static String toPatternMatcher(String str, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.replaceAll("");
    }

    public static String getCamelName(String name) {
        if (name == null) {
            return null;
        } else {
            StringBuffer sb = new StringBuffer();
            Matcher m = Pattern.compile("_([a-z])").matcher(name.toLowerCase());

            while (m.find()) {
                m.appendReplacement(sb, m.group(1).toUpperCase());
            }

            m.appendTail(sb);
            return sb.toString();
        }
    }

    public static String convertCamel(String str, boolean isFirstUpper) {
        if (isNullOrEmpty(str)) {
            return str;
        } else {
            String[] arr = str.split("_");
            StringBuffer sb = new StringBuffer();
            int len = arr.length;

            for (int i = 0; i < len; ++i) {
                String s = arr[i];
                if (s.length() > 0) {
                    String tail = s.substring(1).toLowerCase();
                    String head = s.substring(0, 1);
                    if (i == 0 && !isFirstUpper) {
                        head = head.toLowerCase();
                    } else {
                        head = head.toUpperCase();
                    }

                    sb.append(head).append(tail);
                }
            }

            return sb.toString();
        }
    }
}
