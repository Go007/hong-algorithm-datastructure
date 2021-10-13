package com.hong.util;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author yuanll
 * @date 2021/4/26
 */
public class NumberUtil {

    public static final Long LONG_ZERO = Long.valueOf(0L);

    public static final Integer INTEGER_ZERO = Integer.valueOf(0);

    public static boolean isLong(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Integer toInt(String text) {
        try {
            return Integer.valueOf(text);
        } catch (Exception e) {
            return null;
        }
    }

    public static Long toLong(String text) {
        try {
            return Long.valueOf(text);
        } catch (Exception e) {
            return null;
        }
    }

    public static Long toLong(BigDecimal bigDecimal) {
        return bigDecimal != null ? bigDecimal.longValue() : null;
    }

    public static Integer toInteger(BigDecimal bigDecimal) {
        return bigDecimal != null ? bigDecimal.intValue() : null;
    }

    public static BigDecimal toBigDecimal(Long value) {
        return value != null ? new BigDecimal(value.toString()) : null;
    }

    public static BigDecimal toBigDecimal(Integer value) {
        return value != null ? new BigDecimal(value.toString()) : null;
    }

    public static BigDecimal toBigDecimal(Double value) {
        return value != null ? BigDecimal.valueOf(value) : null;
    }

    public static String toString(Integer num) {
        return num != null ? num.toString() : null;
    }

    public static String toString(Integer num, String defVal) {
        return num != null ? num.toString() : defVal;
    }

    public static String toString(Long num) {
        return num != null ? num.toString() : null;
    }

    public static String toString(Long num, String defVal) {
        return num != null ? num.toString() : defVal;
    }

    public static BigDecimal sum(BigDecimal... list) {
        BigDecimal result = BigDecimal.ZERO;
        if (list == null) {
            return result;
        }
        for (BigDecimal one : list) {
            if (one != null) {
                result = result.add(one);
            }
        }
        return result;
    }

    public static boolean isNull(Integer number) {
        return number == null || INTEGER_ZERO.compareTo(number) == 0;
    }

    public static boolean isNull(Long number) {
        return number == null || LONG_ZERO.compareTo(number) == 0;
    }

    public static boolean isNull(BigDecimal number) {
        return number == null || BigDecimal.ZERO.compareTo(number) == 0;
    }

    public static boolean isNotNull(Integer number) {
        return number != null && INTEGER_ZERO.compareTo(number) != 0;
    }

    public static boolean isNotNull(Long number) {
        return number != null && LONG_ZERO.compareTo(number) != 0;
    }

    public static boolean isNotNull(BigDecimal number) {
        return number != null && BigDecimal.ZERO.compareTo(number) != 0;
    }

    public static BigDecimal formatDecimalToNoNull(BigDecimal number) {
        if (number != null && number.compareTo(BigDecimal.ZERO) <= 0) {
            return null;
        }
        return number;
    }

    public static Long formatLongToNull(long number) {
        if (number <= 0) {
            return null;
        }
        return number;
    }

    /**
     * 设置小数位数
     * @param value : 待处理的数值
     * @param scale : 需要保留的小数位数
     * @return {@link BigDecimal}
     */
    public static BigDecimal toFixScale(BigDecimal value, int scale) {
        if (Objects.nonNull(value) && value.doubleValue() > 0) {
            int index = value.toString().indexOf(".");
            if (value.toString().length() - index - 1 > scale) {
                return value.setScale(scale, BigDecimal.ROUND_HALF_UP);
            }
        }
        return value;
    }
}
