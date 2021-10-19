package com.hong.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @description: 费用金额处理工具
 * @author: 王宏
 * @email hong.wang8@amh-group.com
 * @date: 2021/10/13 10:30
 * @version: 1.0
 */
public class FeeAmountUtil {

    private static final BigDecimal HUNDRED_BD = new BigDecimal("100");
    private static final DecimalFormat df = new DecimalFormat("#");

    /**
     * 将带有小数点且以分为单位的金额四舍五入，去除小数点
     *
     * @param amount
     * @return
     */
    public static BigDecimal eliminateDecimalPointWithFenUnit(BigDecimal amount) {
        if (amount == null) {
            return null;
        }

//        BigDecimal longAmount = new BigDecimal(amount.longValue());
//        // 判断BigDecimal是否有小数位
//        if (longAmount.compareTo(amount) != 0) {
//            // 带有小数位, 根据小数位后的第一位四舍五入决定是否进位
//            amount = longAmount;
//        }

        return new BigDecimal(df.format(amount));
    }

    public static BigDecimal eliminateDecimalPointWithFenUnit(String amount) {
        if (StringUtils.isBlank(amount)) {
            return null;
        }

        if (!NumberUtils.isCreatable(amount)) { //非数字
            return null;
        }

        return eliminateDecimalPointWithFenUnit(new BigDecimal(amount));
    }

    /**
     * 将带有小数点且以元为单位的金额四舍五入转换为分，去除小数点
     *
     * @param amount
     * @return
     */
    public static BigDecimal eliminateDecimalPointWithYuanUnit(BigDecimal amount) {
        if (amount == null) {
            return null;
        }

        BigDecimal longAmount = new BigDecimal(amount.longValue());
        if (longAmount.compareTo(amount) == 0) {
            amount = amount.multiply(HUNDRED_BD);
        } else {
            amount = NumberUtil.toFixScale(amount, 2).multiply(HUNDRED_BD);
        }

        return eliminateDecimalPointWithFenUnit(amount);
    }

    public static BigDecimal eliminateDecimalPointWithYuanUnit(String amount) {
        if (StringUtils.isBlank(amount)) {
            return null;
        }

        if (!NumberUtils.isCreatable(amount)) { //非数字
            return null;
        }

        return eliminateDecimalPointWithYuanUnit(new BigDecimal(amount));
    }

    public static void main(String[] args) {
        BigDecimal bd = new BigDecimal("0.6");
//        String s = bd.toPlainString();
//        System.out.println(s.contains("."));
//        String[] split = s.split(".");
//        DecimalFormat df = new DecimalFormat("#");
//        String format = df.format(bd);
//        System.out.println(format);

        System.out.println(eliminateDecimalPointWithFenUnit(bd));

        String str = "123.54";
        System.out.println(eliminateDecimalPointWithFenUnit(str));

        BigDecimal bd2 = new BigDecimal("123.464609");
        System.out.println(eliminateDecimalPointWithYuanUnit(bd2));

        String ss = "1672345";
        List<String> list = Arrays.asList(ss);
        List<MoneyDTO> convert = convert(list);
        convert.forEach(s -> System.out.println(s));

        System.out.println(ss.substring(0, 5)); // index [);
        System.out.println(Long.parseLong(ss) / 10000);

        System.out.println("=================");
        double money = 123456;
        System.out.println(hangeToBig(money));

        System.out.println("++++++++++++++++++++++++");
        String m = "8582008.67";
        MoneyDTO moneyDTO = convert(m);
        System.out.println(moneyDTO);
    }

    public static MoneyDTO convert(String money) {
        if (StringUtils.isEmpty(money) || !NumberUtils.isCreatable(money)) {
            return new MoneyDTO();
        }

        BigDecimal bd = new BigDecimal(money);
        long amount = toFixScale(bd,0).longValue();

        MoneyDTO moneyDTO = new MoneyDTO();
        long wan = amount / 10000;
        if (wan > 0) {
            moneyDTO.setWan(wan + "");
            amount = amount - wan * 10000;
        }

        if (amount > 0) {
            long qian = amount / 1000;
            if (qian > 0) {
                moneyDTO.setQian(qian + "");
                amount = amount - qian * 1000;
            }
            if (amount > 0) {
                long bai = amount / 100;
                if (bai > 0) {
                    moneyDTO.setBai(bai + "");
                    amount = amount - bai * 100;
                }
                if (amount > 0) {
                    long shi = amount / 10;
                    if (shi > 0) {
                        moneyDTO.setShi(shi + "");
                        amount = amount - shi * 10;
                    }
                    if (amount > 0) {
                        moneyDTO.setYuan(amount + "");
                    }
                }
            }
        }

        return moneyDTO;
    }

    public static BigDecimal toFixScale(BigDecimal value, int scale) {
        if (Objects.nonNull(value) && value.doubleValue() > 0) {
            int index = value.toString().indexOf(".");
            if (value.toString().length() - index - 1 > scale) {
                return value.setScale(scale, BigDecimal.ROUND_HALF_UP);
            }
        }
        return value;
    }

    /**
     * 人民币转成大写
     *
     * @param value
     * @return String
     */
    public static String hangeToBig(double value) {
        char[] hunit = {'拾', '佰', '仟'}; // 段内位置表示
        char[] vunit = {'万', '亿'}; // 段名表示
        char[] digit = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'}; // 数字表示
        long midVal = (long) (value * 100); // 转化成整形
        String valStr = String.valueOf(midVal); // 转化成字符串
        String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
        String rail = valStr.substring(valStr.length() - 2); // 取小数部分
        String prefix = ""; // 整数部分转化的结果
        String suffix = ""; // 小数部分转化的结果
// 处理小数点后面的数
        if (rail.equals("00")) { // 如果小数部分为0
            suffix = "整";
        } else {
            suffix = digit[rail.charAt(0) - '0'] + "角"
                    + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
        }
// 处理小数点前面的数
        char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeroSerNum = 0; // 连续出现0的次数
        for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
            int idx = (chDig.length - i - 1) % 4; // 取段内位置
            int vidx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0') { // 如果当前字符是0
                zeroSerNum++; // 连续0次数递增
                if (zero == '0') { // 标志
                    zero = digit[0];
                } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix += vunit[vidx - 1];
                    zero = '0';
                }
                continue;
            }
            zeroSerNum = 0; // 连续0次数清零
            if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
                prefix += zero;
                zero = '0';
            }
            prefix += digit[chDig[i] - '0']; // 转化该数字表示
            if (idx > 0)
                prefix += hunit[idx - 1];
            if (idx == 0 && vidx > 0) {
                prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
            }
        }
        if (prefix.length() > 0)
            prefix += '圆'; // 如果整数部分存在,则有圆的字样
        return prefix + suffix; // 返回正确表示
    }

    public static List<MoneyDTO> convert(List<String> moneyList) {
        if (moneyList == null || moneyList.size() == 0) {
            return new ArrayList<>();
        }

        List<MoneyDTO> result = new ArrayList<>();
        for (String s : moneyList) {
            if (!NumberUtils.isCreatable(s)) { //非数字
                continue;
            }

            Long amount = null;
            if (s.contains(".")) {
                BigDecimal bd = toFixScale(new BigDecimal(s), 0);
                amount = bd.longValue();
            } else {
                amount = Long.parseLong(s);
            }

            MoneyDTO moneyDTO = null;
            long wan = amount / 10000 % 10;
            if (wan > 0) {
                String wanStr = s.substring(0, 5);

            }

            result.add(new MoneyDTO(amount / 10000 % 10 + "", amount / 1000 % 10 + "", amount / 100 % 10 + "", amount / 10 % 10 + "", amount % 10 + ""));
        }

        return result;
    }

    public static class MoneyDTO {
        private String wan = "";
        private String qian = "";
        private String bai = "";
        private String shi = "";
        private String yuan = "";

        public MoneyDTO() {
        }

        public MoneyDTO(String wan, String qian, String bai, String shi, String yuan) {
            this.wan = "0".equals(wan) ? "" : wan;
            this.qian = "0".equals(qian) ? "" : qian;
            ;
            this.bai = "0".equals(bai) ? "" : bai;
            ;
            this.shi = "0".equals(shi) ? "" : shi;
            ;
            this.yuan = "0".equals(yuan) ? "" : yuan;
            ;
        }

        public String getWan() {
            return wan;
        }

        public void setWan(String wan) {
            this.wan = wan;
        }

        public String getQian() {
            return qian;
        }

        public void setQian(String qian) {
            this.qian = qian;
        }

        public String getBai() {
            return bai;
        }

        public void setBai(String bai) {
            this.bai = bai;
        }

        public String getShi() {
            return shi;
        }

        public void setShi(String shi) {
            this.shi = shi;
        }

        public String getYuan() {
            return yuan;
        }

        public void setYuan(String yuan) {
            this.yuan = yuan;
        }

        @Override
        public String toString() {
            return "MoneyDTO{" +
                    "wan='" + wan + '\'' +
                    ", qian='" + qian + '\'' +
                    ", bai='" + bai + '\'' +
                    ", shi='" + shi + '\'' +
                    ", yuan='" + yuan + '\'' +
                    '}';
        }
    }
}
