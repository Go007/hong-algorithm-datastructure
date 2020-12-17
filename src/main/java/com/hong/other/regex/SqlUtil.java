package com.hong.other.regex;

import com.hong.util.StringUtil;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author wanghong
 * @Date 2020/12/16 18:31
 * @Version V1.0
 **/
public class SqlUtil {
    private static final Pattern getConditionPattern = Pattern.compile("\\{(\\d+)\\}", 2);
    private static final Pattern getParamPattern = Pattern.compile("\\{(\\w+)\\}", 2);
    private static Pattern getTablePattern = Pattern.compile("\\b(\\w+)\\b((\\s+as\\s+)|\\s+)\\b(du_\\w+)\\b", 2);
    private static Pattern getColumnPattern = Pattern.compile("(SELECT\\s+|,\\s*)\\((.+?)(as\\\\s+)?\\bcu___(\\w+)___(\\w+)\\b\\)(\\s*,?)", 34);
    private static final Pattern removeAlltrn = Pattern.compile("\t|\r|\n");

    public SqlUtil() {
    }

    public static String replaceAlltrn(String sql) {
        return removeAlltrn.matcher(sql).replaceAll(" ");
    }

    public static String replaceParam(String sql, Map<String, Object> paramValueMap) {
        if (StringUtil.isNullOrEmpty(sql)) {
            return null;
        } else {
            Matcher m = getParamPattern.matcher(sql);
            StringBuffer newSql = new StringBuffer();

            while (m.find()) {
                Object value = paramValueMap.get(m.group(0));
                if (value == null) {
                    System.out.println("替换系统参数错误,未替换参数-->" + m.group(0) + ",sql-->" + sql);
                    throw new RuntimeException("替换系统参数错误,未替换参数:" + m.group(0));
                }

                m.appendReplacement(newSql, value.toString());
            }

            m.appendTail(newSql);
            return newSql.toString();
        }
    }

    public static String replaceCondition(String sql, Map<String, String> map) {
        if (StringUtil.isNullOrEmpty(sql)) {
            return null;
        } else {
            Matcher m = getConditionPattern.matcher(sql);
            StringBuffer newSql = new StringBuffer();

            while (m.find()) {
                m.appendReplacement(newSql, (String) map.get(m.group(1)));
            }

            m.appendTail(newSql);
            return newSql.toString();
        }
    }

    public static String replaceTable(String sql, Map<String, String> tableAuths) {
        sql = replaceAlltrn(sql);
        Matcher m = getTablePattern.matcher(sql);
        StringBuffer newSql = new StringBuffer();

        while (m.find()) {
            String tableSql = tableAuths.get(m.group(1).toLowerCase());
            if (StringUtil.isNullOrEmpty(tableSql)) {
                m.appendReplacement(newSql, m.group(0));
            } else {
                StringBuilder newTable = (new StringBuilder(" (")).append(tableSql).append(") ").append(m.group(4));
                System.out.println("权限SQL替换: " + m.group() + "→" + newTable);
                m.appendReplacement(newSql, newTable.toString());
            }
        }

        m.appendTail(newSql);
        return newSql.toString();
    }

    public static String replaceColumn(String sql, Set<String> blackTableColumns) {
        sql = replaceAlltrn(sql);
        Matcher m = getColumnPattern.matcher(sql);
        StringBuffer newSql = new StringBuffer();
        if (m.find()) {
            if (blackTableColumns.contains(m.group(4).toLowerCase())) {
                if ("select".equals(m.group(1).trim().toLowerCase())) {
                    m.appendReplacement(newSql, "SELECT ");
                } else {
                    m.appendReplacement(newSql, m.group(6));
                }
            } else {
                m.appendReplacement(newSql, buildResultColumn(m));
            }
        }

        while (m.find()) {
            if (blackTableColumns.contains(m.group(4).toLowerCase())) {
                m.appendReplacement(newSql, m.group(6));
            } else {
                m.appendReplacement(newSql, buildResultColumn(m));
            }
        }

        m.appendTail(newSql);
        return newSql.toString();
    }

    private static String buildResultColumn(Matcher m) {
        StringBuilder column = new StringBuilder(" ");
        column.append(m.group(1)).append("");
        column.append(m.group(2)).append(" ");
        column.append(m.group(5)).append(" ");
        column.append(m.group(6)).append(" ");
        return column.toString();
    }

    public static void setTableReplacePattern(String tableReplacePattern) {
        if (!StringUtil.isNullOrEmpty(tableReplacePattern)) {
            getTablePattern = Pattern.compile(tableReplacePattern, 2);
        }
    }

    public static void setColumnReplacePattern(String columnReplacePattern) {
        if (!StringUtil.isNullOrEmpty(columnReplacePattern)) {
            getColumnPattern = Pattern.compile(columnReplacePattern, 34);
        }
    }

}
