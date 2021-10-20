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
        System.out.println(ss.substring(0, 5)); // index [);
        System.out.println(Long.parseLong(ss) / 10000);

        System.out.println("=================");
        String money = "767120234.32";
        System.out.println(hangeToBig(money));

        System.out.println("++++++++++++++++++++++++");
        String m = "578120234.67";
        MoneyDTO moneyDTO = convert(m);
        System.out.println(moneyDTO);

        System.out.println(Math.pow(10, 0));
        System.out.println(12345 - Math.pow(10, 2) * 123);

        System.out.println("****************************");
        String mm = "56789";
        System.out.println(convertMoney(mm));
        System.out.println(hangeToBig(mm));
        System.out.println(convert(mm));
        System.out.println(convertAmountMap2(mm));
    }

    /**
     * 转换以元为单位的字符串数值为特定bean
     *
     * @param money
     * @return
     */
    public static MoneyDTO convertMoney(String money) {
        if (StringUtils.isEmpty(money) || !NumberUtils.isCreatable(money)) {
            return new MoneyDTO();
        }

        BigDecimal bdFenUnit = eliminateDecimalPointWithYuanUnit(money);

        MoneyDTO moneyDTO = new MoneyDTO();
        List<String> list = convertMoney(bdFenUnit.toPlainString(), 10);
        System.out.println(list);

        moneyDTO.setYi(list.get(0));
        moneyDTO.setWan(list.get(1));
        moneyDTO.setQian(list.get(2));
        moneyDTO.setBai(list.get(3));
        moneyDTO.setShi(list.get(4));
        moneyDTO.setYuan(list.get(5));
        moneyDTO.setJiao(list.get(6));
        moneyDTO.setFen(list.get(7));

        return moneyDTO;
    }

    public static Map<String, String> convertAmountMap2(String money) {
        Map<String, String> map = new LinkedHashMap<>();
        if (StringUtils.isEmpty(money) || !NumberUtils.isCreatable(money)) {
            return map;
        }

        String[] digit = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] amountUnit = {"分", "角", "元", "十", "百", "千", "万", "十万", "百万", "千万", "亿"};
        String s = eliminateDecimalPointWithYuanUnit(money).toPlainString();
        if (s.length() > 11) {
            return map;
        }
        char[] chars = new StringBuilder(s).reverse().toString().toCharArray();
        int len = chars.length;
        for (int i = 0; i < amountUnit.length; i++) {
            if (i > len - 1) {
                map.put(amountUnit[i], "零");
            } else {
                map.put(amountUnit[i], digit[chars[i] - '0']);
            }
        }

        return map;
    }

    /**
     * 转换以元为单位的金额
     *
     * @param money
     * @return
     */
    public static Map<String, String> convertAmountMap(String money) {
        Map<String, String> map = new LinkedHashMap<>();
        if (StringUtils.isEmpty(money) || !NumberUtils.isCreatable(money)) {
            return map;
        }

        String[] digit = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        String[] amountUnit = {"分", "角", "元", "十", "百", "千", "万", "十万", "百万", "千万", "亿"};
        String s = new StringBuilder(eliminateDecimalPointWithYuanUnit(money).toPlainString()).reverse().toString();

        String overYiStr = null;
        char[] chars = null;
        if (s.length() > 11) {
            overYiStr = s.substring(11);
            chars = s.substring(0, 11).toCharArray();
        } else {
            chars = s.toCharArray();
        }

        for (int i = 0; i < chars.length; i++) {
            map.put(amountUnit[i], digit[chars[i] - '0']);
        }

        if (overYiStr != null) {
            String yiStr = s.substring(10);

        }

        return map;
    }


    public static List<String> convertMoney(String money, int num) {
        BigDecimal bd = new BigDecimal(money);
        long amount = toFixScale(bd, 0).longValue();
        List<String> list = new ArrayList<>(num + 1);
        doConvert(amount, num, list);
        return list;
    }

    private static void doConvert(Long amount, int num, List<String> list) {
        if (num < 0) {
            return;
        }

        if (amount > 0) {
            long pow = (long) Math.pow(10, num);
            long temp = amount / pow;
            if (temp > 0) {
                list.add(temp + "");
                amount -= temp * pow;
            } else {
                list.add("0");
            }
        } else {
            list.add("0");
        }

        doConvert(amount, --num, list);
    }

    /**
     * 转换以元为单位的字符串数值为特定bean
     *
     * @param money
     * @return
     */
    public static MoneyDTO convert(String money) {
        if (StringUtils.isEmpty(money) || !NumberUtils.isCreatable(money)) {
            return new MoneyDTO();
        }

        BigDecimal bdFenUnit = eliminateDecimalPointWithYuanUnit(money);
        return convertWithFenUnit(bdFenUnit.toPlainString());
    }

    /**
     * 转换以分为单位的字符串数值为特定bean
     *
     * @param money
     * @return
     */
    public static MoneyDTO convertWithFenUnit(String money) {
        if (StringUtils.isEmpty(money) || !NumberUtils.isCreatable(money)) {
            return new MoneyDTO();
        }

        MoneyDTO moneyDTO = new MoneyDTO();
        long amount = Long.parseLong(money);
        if (amount > 0) {
            long yi = amount / (long) Math.pow(10, 10);
            moneyDTO.setYi(yi + "");
            if (yi > 0) {
                amount -= yi * (long) Math.pow(10, 10);
            }
            if (amount > 0) {
                long wan = amount / 1000000;
                moneyDTO.setWan(wan + "");
                if (wan > 0) {
                    amount = amount - wan * 1000000;
                }
                if (amount > 0) {
                    long qian = amount / 100000;
                    moneyDTO.setQian(qian + "");
                    if (qian > 0) {
                        amount = amount - qian * 100000;
                    }
                    if (amount > 0) {
                        long bai = amount / 10000;
                        moneyDTO.setBai(bai + "");
                        if (bai > 0) {
                            amount = amount - bai * 10000;
                        }
                        if (amount > 0) {
                            long shi = amount / 1000;
                            moneyDTO.setShi(shi + "");
                            if (shi > 0) {
                                amount = amount - shi * 1000;
                            }
                            if (amount > 0) {
                                long yuan = amount / 100;
                                moneyDTO.setYuan(yuan + "");
                                if (yuan > 0) {
                                    amount -= yuan * 100;
                                }
                                if (amount > 0) {
                                    long jiao = amount / 10;
                                    moneyDTO.setJiao(jiao + "");
                                    if (jiao > 0) {
                                        amount -= jiao * 10;
                                    }
                                    moneyDTO.setFen(amount + "");
                                }
                            }
                        }
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

    private static final BigDecimal BD_HUNDRED = new BigDecimal("100");

    /**
     * 人民币转成大写
     *
     * @param money
     * @return String
     */
    public static String hangeToBig(String money) {
        if (StringUtils.isEmpty(money) || !NumberUtils.isCreatable(money)) {
            return "";
        }

        BigDecimal bd = toFixScale(new BigDecimal(money), 2);

        char[] hunit = {'拾', '佰', '仟'}; // 段内位置表示
        char[] vunit = {'万', '亿'}; // 段名表示
        char[] digit = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'}; // 数字表示
        long midVal = BD_HUNDRED.multiply(bd).longValue(); // 转化成整形
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

    public static class MoneyDTO {
        private String yi = "";
        private String wan = "";
        private String qian = "";
        private String bai = "";
        private String shi = "";
        private String yuan = "";
        private String jiao = "";
        private String fen = "";

        public MoneyDTO() {
        }

//        public MoneyDTO(String wan, String qian, String bai, String shi, String yuan) {
//            this.wan = "0".equals(wan) ? "" : wan;
//            this.qian = "0".equals(qian) ? "" : qian;
//            ;
//            this.bai = "0".equals(bai) ? "" : bai;
//            ;
//            this.shi = "0".equals(shi) ? "" : shi;
//            ;
//            this.yuan = "0".equals(yuan) ? "" : yuan;
//            ;
//        }


        public String getYi() {
            return yi;
        }

        public void setYi(String yi) {
            this.yi = yi;
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

        public String getJiao() {
            return jiao;
        }

        public void setJiao(String jiao) {
            this.jiao = jiao;
        }

        public String getFen() {
            return fen;
        }

        public void setFen(String fen) {
            this.fen = fen;
        }

        @Override
        public String toString() {
            return "MoneyDTO{" +
                    "yi='" + yi + '\'' +
                    ", wan='" + wan + '\'' +
                    ", qian='" + qian + '\'' +
                    ", bai='" + bai + '\'' +
                    ", shi='" + shi + '\'' +
                    ", yuan='" + yuan + '\'' +
                    ", jiao='" + jiao + '\'' +
                    ", fen='" + fen + '\'' +
                    '}';
        }
    }
}
