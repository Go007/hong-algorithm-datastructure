package com.hong.util;

/**
 * @description:
 * @author: 王宏
 * @date: 2021/10/20 14:26
 * @version: 1.0
 */

/**
 * 输入一串数字，输出这串数字对应的中文读法
 * 如：
 * 输入：832900180      输出：八亿三千二百九十万一百八十
 * <p>
 * 思路：1.中文读法都是4个一串，所以我用a和b分别来表示这是哪一段（a=len/4）的第几个(b=len%4)数字
 * 2.遍历字符串中的每一个数字，根据其位置，打印出正确的信息
 * 注意事项：
 * 1.在判断某位是否为0时，呀采用字符'0'而不是简单的数字0；
 * 2.在某位数字为0时，应该怎么处理，这就涉及到这个数字处在每一段的哪个位置，同一段的其他数字是否为零的情况，
 * 可以用设计测试用例的方法来书写此段代码
 * Created by Administrator on 2017/10/26.
 */
public class Num2Chinese {
    public static void main(String[] args) {
//        char[] arr = {'十', '百', '千'};
//        char[] brr = {'万', '亿'};
//        char[] crr = {'一', '二', '三', '四', '五', '六', '七', '八', '九'};

        char[] arr = {'拾', '佰', '仟' };
        char[] brr = {'万', '亿'};
        char[] crr = {'壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'};
        String str = "3123456789";
        int len = str.length();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != 0) {
                break;
            } else {
                len--;
            }
        }
        int a = len / 4;
        int b = len % 4;
        if (b == 0) {
            b = 4;
            a = a - 1;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - len; i < str.length(); ) {
            boolean flag = false;
            while (b >= 1) {
                if (b == 3 && str.charAt(i) == '0' && str.charAt(i - 1) != '0' && str.charAt(i + 2) != '0' && str.charAt(i + 1) != '0') {
                    System.out.print("零");
                } else if (b == 2 && str.charAt(i) == '0' && str.charAt(i - 1) != '0' && str.charAt(i + 1) != '0') {
                    System.out.print("零");
                } else if (str.charAt(i) == '0') {
                    ;
                } else if (b >= 2) {
                    flag = true;
                    System.out.print(crr[str.charAt(i) - '1'] + "" + arr[b - 2]);

                } else {
                    flag = true;
                    System.out.print(crr[str.charAt(i) - '1']);
                }
                i++;
                b--;
            }
            if (flag == true && a >= 1) {
                System.out.print(brr[a - 1]);
            }
            a--;
            b = 4;
        }

    }
}
