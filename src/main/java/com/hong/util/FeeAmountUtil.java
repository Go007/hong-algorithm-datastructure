package com.hong.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

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
        BigDecimal bd = new BigDecimal("12368.5678");
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
    }
}
