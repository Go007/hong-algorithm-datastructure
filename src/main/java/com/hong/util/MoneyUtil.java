package com.hong.util;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by wanghong
 * Date 2019-09-19 17:04
 * Description:
 */
public class MoneyUtil {

    public static String format(BigDecimal m){
        String money = "";
        if (m != null){
            BigDecimal k = m.divide(new BigDecimal("1"),9,BigDecimal.ROUND_DOWN);
            if (k.doubleValue() != 0){
                k = m.divide(new BigDecimal("100000000"),2,BigDecimal.ROUND_DOWN);
                NumberFormat ks = NumberFormat.getNumberInstance();
                money = ks.format(k);
                if (money.indexOf(".") > 0){
                    money = money.replaceAll("0+?$",""); // 去掉多余的0
                    money = money.replaceAll("[.]$",""); // 最后一位是 . 去掉
                }
            }else {
                money = "0";
            }
        }
        return money;
    }

    public static void main(String[] args) {
        Double d = 500000.0;
        BigDecimal m = new BigDecimal(d);
        String money = format(m);
        System.out.println(money);
    }

}
