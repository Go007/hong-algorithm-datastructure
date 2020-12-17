package com.hong.other.regex;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: 正则表达式
 * @Author wanghong
 * @Date 2020/12/16 17:50
 * @Version V1.0
 **/
public class TestReg {

    private static final Pattern getConditionPattern = Pattern.compile("\\{(\\d+)\\}", 2);
    private static final Pattern getParamPattern = Pattern.compile("\\{(\\w+)\\}", 2);
    private static Pattern getTablePattern = Pattern.compile("\\b(\\w+)\\b((\\s+as\\s+)|\\s+)\\b(du_\\w+)\\b", 2);
    private static Pattern getColumnPattern = Pattern.compile("(SELECT\\s+|,\\s*)\\((.+?)(as\\\\s+)?\\bcu___(\\w+)___(\\w+)\\b\\)(\\s*,?)", 34);
    private static final Pattern removeAlltrn = Pattern.compile("\t|\r|\n");

    private static final Pattern ROWAUTH_PATTERN = Pattern.compile("\\b([A-Za-z0-9_.]+)\\b\\s*\\/\\*\\s*LT\\s*\\*\\/", 2);

    /**
     * 正则表达式场景测试
     *
     * @param args
     */
    public static void main(String[] args) {
        String oldSql = "select\n" +
                " *\n" +
                " from app_borrower_info /*LT*/ b where 1=1\n";

        String newSql = "select * from app_borrower_info where find_in_set({userId},creator)";
        String userId = "308";

        test0(oldSql, newSql, userId);
        System.out.println("==============================");
        test1(oldSql,userId);
    }

    public static void test0(String oldSql, String newSql, String userId) {
        //替换字符串换行,回车,TAB
        oldSql = removeAlltrn.matcher(oldSql).replaceAll(" ");
        //匹配表名+空格+/*LT*/,以用户权限sql定义进行替换
        newSql = replaceTable(oldSql, newSql, userId);
        System.out.println(oldSql);
        System.out.println(newSql);
    }

    public static void test1(String oldSql, String userId) {
        Map<String, String> tableAuths = new HashMap<>();
        tableAuths.put("app_borrower_info", "select * from app_borrower_info where find_in_set({userId},creator)");
        Set<String> columnAuths = new HashSet();
        String newSql = SqlUtil.replaceColumn(oldSql, columnAuths);
        newSql = SqlUtil.replaceTable(newSql == null ? oldSql : newSql, tableAuths);
        System.out.println(newSql);
    }

    /**
     * 替换字符串中的 换行,回车,TAB
     *
     * @param sql
     * @return
     */
    public static String replaceAlltrn(String sql) {
        return removeAlltrn.matcher(sql).replaceAll(" ");
    }

    // 匹配表名+空格+ /*LT*/ ,以用户权限sql定义进行替换
    public static String replaceTable(String oldSql, String newSql, String userId) {
        StringBuffer sb = new StringBuffer();
        Matcher m = ROWAUTH_PATTERN.matcher(oldSql);
        boolean changed = false;
        while (m.find()) {
            changed = true;
            String targetTableName = m.group(1).toLowerCase();
            if (newSql == null || newSql.length() == 0) {
                m.appendReplacement(sb, m.group(0));
            } else {
                System.out.println("数据行权限执行前：" + oldSql);
                String tableSql = replaceParam(newSql, initParamValue(userId));
                String newTable = "(" + tableSql + ")";
                m.appendReplacement(sb, newTable);
            }
        }
        if (changed) {
            m.appendTail(sb);
            if (!sb.toString().equals("")) {
                System.out.println("数据行权限执行后：" + sb.toString());
            }
            return sb.toString();
        }
        return oldSql;
    }

    public static String replaceParam(String sql, Map<String, Object> paramValueMap) {
        if (sql == null || sql.length() == 0) {
            return null;
        }

        Matcher m = getParamPattern.matcher(sql);
        StringBuffer newSql = new StringBuffer();
        while (m.find()) {
            Object value = paramValueMap.get(m.group(0));
            if (value == null) {
                System.out.println("替换参数错误");
                return null;
            }

            if (value instanceof String) {
                m.appendReplacement(newSql, "'" + value.toString() + "'");
            } else if (value instanceof Integer) {
                m.appendReplacement(newSql, value.toString());
            } else if (value instanceof Long) {
                m.appendReplacement(newSql, value.toString());
            }
        }
        m.appendTail(newSql);
        return newSql.toString();
    }

    public static Map<String, Object> initParamValue(String userId) {
        Map<String, Object> paramValueMap = new HashMap();
        paramValueMap.put("{userId}", userId);

        return paramValueMap;
    }
}
