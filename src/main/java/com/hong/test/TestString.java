package com.hong.test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;

/**
 * @author wanghong
 * @desc:
 * @date 2022/02/16:35
 **/
public class TestString {
    public static void main(String[] args) {
        String s = ":";
        String[] split = s.split(":");
        System.out.println(split.length);


        System.out.println("当天03点的时间戳为：" + getTimeStamp(0,3));
        System.out.println("明天03点的时间戳为：" + getTimeStamp(1,3));
        System.out.println("今天0点的时间戳为：" + getTimeStamp(0,0));
        System.out.println("明天0点的时间戳为：" + getTimeStamp(1,0));
        System.out.println("今天24点的时间戳为：" + getTimeStamp(0,23));
        System.out.println("明天24点的时间戳为：" + getTimeStamp(1,23));

    }

    /**
     * 根据给定的参数获取时间戳
     *
     * @param plusDays  距当天增加的天数，示例：0-表示当天，1-表示明天，依次类推
     * @param hourPoint 小时点，示例：0,1,2,3......23
     * @return
     */
    public static long getTimeStamp(int plusDays,int hourPoint){
        LocalDate localDate = null;
        if (0 == plusDays){
            localDate = LocalDate.now();
        }else {
            // 获取当前日期和时间
            LocalDate currentDate = LocalDate.now();
            localDate = currentDate.plusDays(plusDays);
        }

        LocalTime targetTime = LocalTime.of(hourPoint, 0);

        // 创建目标日期和时间对象
        LocalDateTime targetDateTime = LocalDateTime.of(localDate, targetTime);

        // 将目标日期和时间转换成时间戳
        Instant instant = targetDateTime.atZone(ZoneId.systemDefault()).toInstant();
        long timestamp = instant.getEpochSecond() * 1000L;

        return timestamp;
    }





}
